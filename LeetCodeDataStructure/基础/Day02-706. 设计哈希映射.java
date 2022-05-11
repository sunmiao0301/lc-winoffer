## 第一版 通过 但是题解的思路更标准，应该好好学习一下

执行结果：
通过
显示详情
添加备注

执行用时：
16 ms
, 在所有 Java 提交中击败了
85.78%
的用户
内存消耗：
47.5 MB
, 在所有 Java 提交中击败了
65.82%
的用户
通过测试用例：
36 / 36

class MyHashMap {
    //最多调用 10000 次 put、get 和 remove 方法
    ArrayList<LinkedList<int[]>> main;// = new ArrayList<>();

    public MyHashMap() {
        main = new ArrayList<>(1000);
        for(int i = 0; i < 1000; i++){
            main.add(new LinkedList<>());
        }
        // LinkedList<int[]> bucket = main.get(0);
        // bucket.add(new int[]{0, 1});
    }
    
    public void put(int key, int value) {
        int hashCode = key % 1000;
        LinkedList<int[]> bucket = main.get(hashCode);
        for(int i = 0; i < bucket.size(); i++){
            int[] tmp = bucket.get(i);
            if(tmp[0] == key){
                tmp[1] = value;
                return;
            }
        }
        bucket.add(new int[]{key, value});
    }

    //int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 
    public int get(int key) {
        int hashCode = key % 1000;
        LinkedList<int[]> bucket = main.get(hashCode);
        for(int i = 0; i < bucket.size(); i++){
            int[] tmp = bucket.get(i);
            if(tmp[0] == key){
                return tmp[1];
            }
        }
        return -1;
    }
    
    public void remove(int key) {
        int hashCode = key % 1000;
        LinkedList<int[]> bucket = main.get(hashCode);
        for(int i = 0; i < bucket.size(); i++){
            int[] tmp = bucket.get(i);
            if(tmp[0] == key){
                bucket.remove(i);
                return;
            }
        }
    }
}

## 题解 确实比我的HashMap更标准
class MyHashMap {
    private class Pair {
        private int key;
        private int value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static final int BASE = 769;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashMap() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Pair>();
        }
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                pair.setValue(value);
                return;
            }
        }
        data[h].offerLast(new Pair(key, value));
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.getKey() == key) {
                return pair.value;
            }
        }
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int h = hash(key);
        Iterator<Pair> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Pair pair = iterator.next();
            if (pair.key == key) {
                data[h].remove(pair);
                return;
            }
        }
    }

    private static int hash(int key) {
        return key % BASE;
    }
}
