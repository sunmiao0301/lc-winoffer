## 迭代法很简单 自己能写出来 三个指针解决

## 递归法 没写出来 题解如下
class Solution {
    ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode ret = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return ret;
    }
}
