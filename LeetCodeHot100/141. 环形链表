第一版 用的set 没啥用哦
执行结果：
通过
执行用时：
4 ms
, 在所有 Java 提交中击败了
22.32%
的用户
内存消耗：
39 MB
, 在所有 Java 提交中击败了
92.47%
的用户
通过测试用例：
21 / 21
public class Solution {
    public boolean hasCycle(ListNode head) {
        //进阶：你能用 O(1)（即，常量）内存解决此问题吗？
        HashSet<ListNode> set = new HashSet<>();
        while(head != null && !set.contains(head)){
            set.add(head);
            head = head.next;
        }
        if(head == null)
            return false;
        return true;
    }
}

第二版
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
39.7 MB
, 在所有 Java 提交中击败了
17.47%
的用户
通过测试用例：
21 / 21
public class Solution {
    public boolean hasCycle(ListNode head) {
        //进阶：你能用 O(1)（即，常量）内存解决此问题吗？
        //快慢指针
        //这题好像比我之前做过的一个环形链表要简单，上次的时候还需要求出具体的环的位置
        if(head == null || head.next == null) return false;
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while(slow != fast && fast != null && fast.next != null){//这里的fast.next != null是防止fast遇到NullPointerException
            slow = slow.next;
            fast = fast.next.next;
        }
        if(slow == fast)
            return true;
        return false;
    }
}
