## 第一版 之前写的 直接运行了

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        //链表中节点数目在范围 [0, 300] 内
        ListNode sentinel = new ListNode(-101, head);

        ListNode pSentinel = sentinel;
        ListNode pre = sentinel.next;//pre.val != pSentinel.val
        if(pre == null)return null;
        int tmp = head.val;
        //ListNode cur = pre.next;
        while(pre.next != null) {
            if (pre.next.val == tmp) {//--那么等于这个值的节点都不要
                while (pre.val == tmp) {
                    if(pre.next == null){
                        pSentinel.next = null;
                        return sentinel.next;
                    }
                    pre = pre.next;
                }
                tmp = pre.val;
                continue;
            } else if (pre.next.val != tmp) {
                pSentinel.next = pre;
                pSentinel = pSentinel.next;
                pre = pre.next;
                tmp = pre.val;
            }
        }
        pSentinel.next = pre;
        return sentinel.next;
    }
}
