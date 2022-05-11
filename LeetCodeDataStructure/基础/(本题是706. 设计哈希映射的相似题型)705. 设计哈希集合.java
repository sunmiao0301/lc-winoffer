## 第一版 通过 但是题解的思路更标准，应该好好学习一下

执行结果：
通过
显示详情
添加备注

执行用时：
15 ms
, 在所有 Java 提交中击败了
59.39%
的用户
内存消耗：
49.1 MB
, 在所有 Java 提交中击败了
19.87%
的用户
通过测试用例：
33 / 33

class MyHashSet {
    class Entry{
        private int key;
        public Entry(int key){
            this.key = key;
        }
        public int get(){
            return key;
        }
        // public int set(int key){
        //     this.key = key;
        // }
    }
    ArrayList<LinkedList<Entry>> main;

    public MyHashSet() {
        main = new ArrayList<>(1000);
        for(int i = 0; i < 1000; i++){
            // LinkedList<Entry> tmp = main.get(i);
            // tmp = new LinkedList<Entry>();
            main.add(new LinkedList<Entry>());
        }
    }
    
    public void add(int key) {
        int hash = key % 1000;
        LinkedList<Entry> bucket = main.get(hash);
        for(int i = 0; i < bucket.size(); i++){
            if(bucket.get(i).get() == key){
                return;
            }
        }
        bucket.add(new Entry(key));
    }
    
    public void remove(int key) {
        int hash = key % 1000;
        LinkedList<Entry> bucket = main.get(hash);
        for(int i = 0; i < bucket.size(); i++){
            if(bucket.get(i).get() == key){
                bucket.remove(i);
                return;
            }
        }
    }
    
    public boolean contains(int key) {
        int hash = key % 1000;
        LinkedList<Entry> bucket = main.get(hash);
        for(int i = 0; i < bucket.size(); i++){
            if(bucket.get(i).get() == key){
                return true;
            }
        }
        return false;
    }
}

## 题解
class MyHashSet {
    private static final int BASE = 769;
    private LinkedList[] data;

    /** Initialize your data structure here. */
    public MyHashSet() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<Integer>();
        }
    }
    
    public void add(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return;
            }
        }
        data[h].offerLast(key);
    }
    
    public void remove(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                data[h].remove(element);
                return;
            }
        }
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int h = hash(key);
        Iterator<Integer> iterator = data[h].iterator();
        while (iterator.hasNext()) {
            Integer element = iterator.next();
            if (element == key) {
                return true;
            }
        }
        return false;
    }

    private static int hash(int key) {
        return key % BASE;
    }
}
