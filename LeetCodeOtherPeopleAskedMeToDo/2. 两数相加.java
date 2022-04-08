## 第一版
执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
41.2 MB
, 在所有 Java 提交中击败了
57.93%
的用户
通过测试用例：
1568 / 1568
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //每个链表中的节点数在范围 [1, 100] 内
        ListNode sentinel = new ListNode(0);
        ListNode p = sentinel;
        int carry = 0;
        while(l1 != null && l2 != null){
            int sum = l1.val + l2.val;
            if(carry == 1){
                sum = sum + 1;
            }
            carry = 0;
            if(sum >= 10){
                sum = sum % 10;
                carry = 1;
            }
            p.next = new ListNode(sum);
            p = p.next;

            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode tmp = l1 == null ? l2 : l1;
        while(tmp != null){
            int sum = tmp.val + carry;
            carry = 0;
            if(sum >= 10){
                sum = sum % 10;
                carry = 1;
            }
            p.next = new ListNode(sum);
            p = p.next;

            tmp = tmp.next;
        }
        if(carry == 1){
            p.next = new ListNode(1);
        }
        return sentinel.next;
    }
}
