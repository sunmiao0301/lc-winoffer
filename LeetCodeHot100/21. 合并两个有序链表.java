第一版
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
37.8 MB
, 在所有 Java 提交中击败了
42.72%
的用户
通过测试用例：
208 / 208
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //升序 但不是严格递增
        if(list1 == null || list2 == null)return list1 == null ? list2 : list1;
        ListNode head = list1.val >= list2.val ? list2 : list1;
        ListNode ret = head;
        ListNode a = head.next;
        ListNode b = list1.val >= list2.val ? list1 : list2;
        while(a != null || b != null){
            if(a == null || b == null){
                head.next = (a == null) ? b : a;
                break;
            }
            if(a.val >= b.val){
                head.next = b;
                head = head.next;
                b = b.next;
            }
            else{
                head.next = a;
                head = head.next;
                a = a.next;
            }
        }
        return ret;
    }
}

####
我的解法属于迭代法
另有递归法
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        //这里的l1.next = ...
        //有点优美
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }
}
