//第一版 一遍过 为了省空间 用的是迭代而不是递归 效果不错
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
35.9 MB
, 在所有 Java 提交中击败了
78.93%
的用户
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
 /*
 思路是两两处理
 每处理一个结点 记得存储其后一个结点
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
    if(head == null || head.next == null)return head;···································无结点或是只有一个结点
    if(head.next.next == null || head.next.next.next == null){··························两个结点或是三个结点
        ListNode temp = head.next.next;
        ListNode ret = head.next;
        head.next.next = head;
        head.next = temp;
        return ret;
    }
    ListNode ret = head.next;···························································四个结点及以上
    ListNode temp2 = head;
    ListNode temp1 = head.next;
    while(head.next.next != null && head.next.next.next != null){//head != null && head.next != null && 好像不需要
        head.next = head.next.next.next;//1
        temp2 = temp1.next;//存储3 也就是下一个head'
        temp1.next = head;
        head = temp2;
        temp1 = head.next;
    }
    head.next = temp1.next;·····························································四个结点及以上时，最后的处理（因为head.next.next != null && head.next.next.next != null)导致最后两个结点没处理
    temp1.next = head;
    return ret;
    }
}

//第二版 递归 自己写的
递归很难想出

//第三版 标准题解：

方法一：递归

class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }
}
复杂度分析
时间复杂度：O(n)O(n)，其中 nn 是链表的节点数量。需要对每个节点进行更新指针的操作。
空间复杂度：O(n)O(n)，其中 nn 是链表的节点数量。空间复杂度主要取决于递归调用的栈空间

方法二：迭代

class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }
}
复杂度分析
时间复杂度：O(n)O(n)，其中 nn 是链表的节点数量。需要对每个节点进行更新指针的操作。
空间复杂度：O(1)O(1)。

