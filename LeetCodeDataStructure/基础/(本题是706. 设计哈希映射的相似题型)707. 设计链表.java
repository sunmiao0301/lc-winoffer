## 设计链表绝不简单，需要考虑的东西还是很多的！
## 并且我最后写出来的这版也肯定不是最优的写法！
## 难度在于需要考虑很多判断条件之间的先后关系！
## 也就是说，对于
if(a)
else if(b)
和
if(b)
else if(a)
  
## 两者运行得到的结果可能是完全不同的！（因为index和size两个关键因素的原因，很多条件之间存在包含关系
## （ if - else if 如果不存在包含关系，则两者的先后顺序对程序是不影响的！）

执行结果：
通过
显示详情
添加备注

执行用时：
7 ms
, 在所有 Java 提交中击败了
86.53%
的用户
内存消耗：
41.6 MB
, 在所有 Java 提交中击败了
69.91%
的用户
通过测试用例：
64 / 64

class MyLinkedList {
    int size;
    class ListNode{
        int val;
        ListNode pre;
        ListNode next;
        ListNode(int val){
            this.val = val;
            this.pre = null;
            this.next = null;
        }
    }
    ListNode head;
    ListNode tail;
    //双链表实现
    public MyLinkedList() {
        size = 0;
        head = null;
        tail = null;
    }
    
    public int get(int index) {

        // if(size == 1)return -1;
        if(index < 0 || index >= size){
            return -1;
        }

        ListNode p = head;
        while(index > 0){
            p = p.next;
            index--;
        }
        return p.val;
    }

    public void addFirstNode(int val){
        ListNode p = new ListNode(val);
        head = p;
        tail = p;
        size++;
    }
    
    public void addAtHead(int val) {
        if(size == 0){
            addFirstNode(val);
            return;
        }
        ListNode p = new ListNode(val);
        p.next = head;
        head.pre = p;
        head = p;
        size++;
    }
    
    public void addAtTail(int val) {
        if(size == 0){
            addFirstNode(val);
            return;
        }
        ListNode p = new ListNode(val);
        tail.next = p;
        p.pre = tail;
        tail = p;
        size++;
    }
    
    public void addAtIndex(int index, int val) {
        // if(size == 0){
        //     addFirstNode(val);
        // }
        if(index <= 0){
            addAtHead(val);
        }
        //如果 index 大于链表长度，则不会插入节点。
        else if(index == size){
            addAtTail(val);
        }
        else if(index > size){
            return;
        }
        else{
            //第 index 个节点之前添加 -- 假设链表中的所有节点都是 0-index 的。
            ListNode p = head;
            ListNode add = new ListNode(val);
            while(index > 1){
                p = p.next;
                index--;
            }
            p.next.pre = add;
            add.next = p.next;
            p.next = add;
            add.pre = p;
            size++;
        }
    }
    
    public void deleteAtIndex(int index) {
        // 如果索引 index 有效，则删除链表中的第 index
        if((index < 0) || (index >= size)){
            return;
        }
        else if(size == 1){
            head = null;
            tail = null;
        }
        else if(index == 0){
            head = head.next;
            head.pre = null;
        }
        else if(index == size - 1){
            tail = tail.pre;
            tail.next = null;
        }
        else{
            ListNode p = head;
            while(index > 0){
                p = p.next;
                index--;
            }
            p.pre.next = p.next;
            p.next.pre = p.pre;
        }
        size--;
    }
}

## 题解
"https://leetcode.cn/problems/design-linked-list/solution/she-ji-lian-biao-by-leetcode/
