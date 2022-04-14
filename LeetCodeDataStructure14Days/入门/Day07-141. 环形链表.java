## 第一版 100%
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode nextIsHead = new ListNode(0, head);
        ListNode slow = nextIsHead;
        ListNode fast = head;
        while(slow != fast && fast != null){
            slow = slow.next;
            fast = fast.next;
            if(fast == null)
                return false;
            fast = fast.next;
        }
        if(slow == fast)
            return true;
        return false;
    }
}

## 标准题解
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
