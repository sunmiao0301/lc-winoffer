## 第一版

执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
98.43%
的用户
内存消耗：
44.4 MB
, 在所有 Java 提交中击败了
29.01%
的用户
通过测试用例：
39 / 39

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int flagA = 0;
        int flagB = 0;
        ListNode A = headA;
        ListNode B = headB;
        while(A != B){
            if(A == null && flagA == 0){
                A = headB;
                flagA = 1;
            }
            else if(A == null && flagA == 1){
                return null;
            }
            else{
                A = A.next;
            }

            if(B == null && flagB == 0){
                B = headA;
                flagB = 1;
            }
            else if(B == null && flagB== 1){
                return null;
            }
            else{
                B = B.next;
            }
        }
        return A;
    }
}

## 题解
## 之所以题解可以写的如此简单 是因为 pA pB 如果没有相交，那么最终他们会同时为null --> 所以仍旧满足 pA == pB

public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
