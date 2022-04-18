## 第一版 用了三个指针 速度慢了
执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
63.25%
的用户
内存消耗：
42.1 MB
, 在所有 Java 提交中击败了
39.48%
的用户
通过测试用例：
66 / 66
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode sentinel = new ListNode(0, head);
        ListNode pre = sentinel;
        ListNode cur = head;
        if(head == null)return null;
        ListNode nex = head.next;
        while(nex != null){
            if(cur.val == val){
                pre.next = nex;
                cur = nex;
                nex = nex.next;
            }
            else{
                pre = pre.next;
                cur = cur.next;
                nex = nex.next;
            }
        }
        if(cur.val == val){
            pre.next = null;
        }
        return sentinel.next;
    }
}

## 自己之前写的 100%
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        //如Berkeley所说 如果链表中各个节点地位不一致 那么特殊情况的考虑会把代码变得极丑 哨兵节点能够解决这问题
        ListNode sentinel = new ListNode(301, head);
        ListNode p = sentinel;
        while(p.next != null){
            if(p.next.val == val){
                p.next = p.next.next;
            }
            else{
                p = p.next;
            }
        }
        return sentinel.next;
    }  
}
