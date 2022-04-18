## 第一版 虽然写出来100% 但是思路还是有一点点模糊的
## 我的思路是：建立一个哨兵节点，然后一个负责整理节点的pSentinel,一个负责去除重复节点的pre
## pre负责在每一个“不重复的节点”处停下，然后pSentinel指向pre并pSentinel = pSentinel.next，然后pre继续找寻。

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
41.1 MB
, 在所有 Java 提交中击败了
20.08%
的用户
通过测试用例：
166 / 166
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

## 标准题解
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0, head);

        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}
