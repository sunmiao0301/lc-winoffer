## 第一版 之前写过 直接运行了

执行结果：
通过
显示详情
添加备注

执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
38.9 MB
, 在所有 Java 提交中击败了
72.34%
的用户
通过测试用例：
55 / 55

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode sentinel = new ListNode(0, head);
        ListNode p = sentinel;
        ListNode fir = head;
        if(head == null) return null;
        ListNode sec = head.next;
        while(sec != null){
            p.next = sec;
            fir.next = sec.next;
            sec.next = fir;

            p = fir;
            fir = fir.next;
            if(fir == null)return sentinel.next;
            sec = fir.next;
        }
        return sentinel.next;
    }
}
