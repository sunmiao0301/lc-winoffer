## 第一版
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
15.80%
的用户
通过测试用例：
166 / 166
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)return head;
        ListNode p = head;
        while(p.next != null){
            if(p.next.val == p.val){
                p.next = p.next.next;
            }
            else{
                p = p.next;
            }

        }
        return head;
    }
}
