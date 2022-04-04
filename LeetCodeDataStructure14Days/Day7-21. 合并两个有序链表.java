## cs61b 提到的哨兵节点还是香啊
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
40.9 MB
, 在所有 Java 提交中击败了
37.07%
的用户
通过测试用例：
208 / 208
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode sentinel = new ListNode();
        ListNode p = sentinel;
        while(list1 != null && list2 != null){
            if(list1.val >= list2.val){
                p.next = list2;
                p = p.next;
                list2 = list2.next;
            }
            else{
                p.next = list1;
                p = p.next;
                list1 = list1.next;
            }
        }
        if(list1 == null)
            p.next = list2;
        else{
            p.next = list1;
        }
        return sentinel.next;
    }
}
