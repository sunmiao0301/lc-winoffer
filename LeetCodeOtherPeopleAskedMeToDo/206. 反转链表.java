## 第一版 迭代很简单 递归的写的如下 但是实际上还是没写出来 实在想不到最后怎么才能返回新的头节点（也就是原链表的尾节点）

已完成
执行用时：0 ms
输入
[1,2,3,4,5]
[1,2,3]
[1,2]
输出
[1]
[1]
[1]
预期结果
[5,4,3,2,1]
[3,2,1]

class Solution {
    ListNode reverseList(ListNode head) {
        if(head.next == null){
            return head;
        }
        reverseList(head.next).next = head;
        head.next = null;
        return head;
    }
}

## 题解 反转链表 迭代版 递归三个部分
class Solution {
    public ListNode reverseList(ListNode head) {
        //1. 递归头  终止递归条件
        if(head == null || head.next == null) return head;
        //2. 递归体  自顶向下深入
        ListNode ret = reverseList(head.next);
        //3. 回溯    自底向上跳出
        head.next.next = head;
        head.next = null;
        return ret;
    }
}
