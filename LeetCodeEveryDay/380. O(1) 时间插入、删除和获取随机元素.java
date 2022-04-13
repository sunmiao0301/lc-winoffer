## 第一版 我想到的思路是ArrayList和Set实现 -- ArrayList的意义在于 getRandom() 函数
但是考虑短了 因为删除的时候必须是同步的，所以应该换成Map得到数组的下标，方便我们同步list和set，此外，方便删除，用LinkedList
但是这样又出现一个问题 那就是我们删除的时候，下标发生了变化怎么办？想到了 不必同步 删除的时候不变list，直接map里面覆盖就行了。
## 于是我们最终自己写出的第一版如下，但是未通过: -- 思考了一下，想到了原因，比如我在删除之后，紧接着getRandom，就可能会得到一个已经删除的元素

class RandomizedSet {
    //每个函数的 平均 时间复杂度为 O(1) 。
    List<Integer> list;
    Map<Integer, Integer> map;

    Random random;

    public RandomizedSet() {
        list = new LinkedList<Integer>();
        map = new HashMap<>();

        random = new Random();
    }
    
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }
        else{
            list.add(val);
            map.put(val, list.size());
            return true;
        }
    }
    
    public boolean remove(int val) {
        if(map.containsKey(val)){
            map.remove(val);
            return true;
        }
        else{
            return false;
        }
    }
    
    public int getRandom() {
        int size = map.size();
        //然后在链表的末端向前size的长度取数
        // 关于生成随机数
        // andom rand=new Random();
        // int n1=rand.nextInt(100);//返回值在范围[0,100) 即[0,99]
        // int n2=rand.nextInt(100)+1;//[1,100]内的随机整数
        int randomIndex = random.nextInt(map.size()) + list.size() - map.size();
        return list.get(randomIndex);
    }
}

## 针对这一点bug 我进行了修改如下（对remove操作进行了修改） 但是还是不行，原因是 如果我们删除的是链表中间的节点，就会导致我们之前存在哈希表中的 节点-数组索引 全部都失效了
    public boolean remove(int val) {
        if(map.containsKey(val)){

            //map.remove(val);
            list.remove(map.get(val));
            map.remove(val);
            return true;
        }
        else{
            return false;
        }
    }

## 看了题解 它的删除操作的思路如下：

删除操作时，首先判断 val 是否在哈希表中，如果不存在则返回 false ，如果存在则删除 val ，操作如下：

从哈希表中获得 val 的下标 index；

将变长数组的最后一个元素 last 移动到下标 index 处，在哈希表中将 last 的下标更新为 index；

在变长数组中删除最后一个元素，在哈希表中删除 val；

返回 true。

删除操作的重点在于将变长数组的最后一个元素移动到待删除元素的下标处，然后删除变长数组的最后一个元素。

理解了这个思路之后，我写出的 remove() 函数如下：
    public boolean remove(int val) {
        if(map.containsKey(val)){
            //要删除的 - index = map.get(val);
            //与最后的节点换一下
            //list.set(index, val) => "vallast = list.get(list.size()-1)"
            //map.put(vallast, index);
            //map.remove(val);
            int lastval = list.get(list.size() - 1);
            int lastNewIndex = map.get(val);
            list.set(lastNewIndex, lastval);
            map.put(lastval, lastNewIndex);
            map.remove(val);
            return true;
        }
        else{
            return false;
        }
    }

所以完整版的也就如下:
class RandomizedSet {
    //每个函数的 平均 时间复杂度为 O(1) 。
    List<Integer> list;
    Map<Integer, Integer> map;

    Random random;

    public RandomizedSet() {
        list = new LinkedList<Integer>();
        map = new HashMap<>();

        random = new Random();
    }
    
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }
        else{
            list.add(val);
            map.put(val, list.size());
            return true;
        }
    }
    
    public boolean remove(int val) {
        if(map.containsKey(val)){
            //要删除的 - index = map.get(val);
            //与最后的节点换一下
            //list.set(index, val) => "vallast = list.get(list.size()-1)"
            //map.put(vallast, index);
            //map.remove(val);
            int lastval = list.get(list.size() - 1);
            int lastNewIndex = map.get(val);

            map.remove(val);
            list.set(lastNewIndex, lastval);
            list.remove(list.size() - 1);
            map.put(lastval, lastNewIndex);
            
            return true;
        }
        else{
            return false;
        }
    }
    
    public int getRandom() {
        //int size = map.size();
        //然后在链表的末端向前size的长度取数
        // 关于生成随机数
        // andom rand=new Random();
        // int n1=rand.nextInt(100);//返回值在范围[0,100) 即[0,99]
        // int n2=rand.nextInt(100)+1;//[1,100]内的随机整数
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}

但是还是不对,问题在于我的remove()函数中的四个主要操作的顺序不对，导致遇到一些样例的时候无法通过，如下样例：
["RandomizedSet","insert","remove","insert"]
[[],[0],[0],[0]]

我原先的顺序是：
            map.remove(val);//4
            list.set(lastNewIndex, lastval);//1
            list.remove(list.size() - 1);//3
            map.put(lastval, lastNewIndex);//2

修改后如下：
            list.set(lastNewIndex, lastval);//1
            
            map.put(lastval, lastNewIndex);//2

            list.remove(list.size() - 1);//3

            map.remove(val);//4

不过其实只要这样就行了：
            list.set(lastNewIndex, lastval);//1
            list.remove(list.size() - 1);//3
            map.put(lastval, lastNewIndex);//2
            
            map.remove(val);//4
也就是这个第四步必须在最后，不然在只有一个值的情况下，就会出现明明被删除，但是map中仍有的情况。

## 最后一版如下：
class RandomizedSet {
    //每个函数的 平均 时间复杂度为 O(1) 。
    List<Integer> list;
    Map<Integer, Integer> map;

    Random random;

    public RandomizedSet() {
        list = new LinkedList<Integer>();
        map = new HashMap<>();

        random = new Random();
    }
    
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }
        else{
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }
    }
    
    public boolean remove(int val) {
        if(map.containsKey(val)){
            //要删除的 - index = map.get(val);
            //与最后的节点换一下
            //list.set(index, val) => "vallast = list.get(list.size()-1)"
            //map.put(vallast, index);
            //map.remove(val);
            int lastval = list.get(list.size() - 1);
            int lastNewIndex = map.get(val);

            
            list.set(lastNewIndex, lastval);//1
            list.remove(list.size() - 1);//3
            map.put(lastval, lastNewIndex);//2
            
            map.remove(val);//4
            
            return true;
        }
        else{
            return false;
        }
    }
    
    public int getRandom() {
        //int size = map.size();
        //然后在链表的末端向前size的长度取数
        // 关于生成随机数
        // andom rand=new Random();
        // int n1=rand.nextInt(100);//返回值在范围[0,100) 即[0,99]
        // int n2=rand.nextInt(100)+1;//[1,100]内的随机整数
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}


