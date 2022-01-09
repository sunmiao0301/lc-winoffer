第一版
执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
99.98%
的用户
内存消耗：
41.3 MB
, 在所有 Java 提交中击败了
35.88%
的用户
通过测试用例：
39 / 39
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int flag = 0;
        ListNode skipA = headA;
        ListNode skipB = headB;
        while(flag < 3){
                if(skipA == skipB)
                    return skipA;
                if(skipA.next == null){
                    skipA = headB;
                    flag++;
                }
                else{
                    skipA = skipA.next;
                }
                if(skipB.next == null){
                    skipB = headA;
                    flag++;
                }
                else{
                    skipB = skipB.next;
                }
        }
        return null;
    }
}

####
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        //下面这三行代码有点美
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        //从这里 return pA 也可以看出
        //不需要像我一样进行一个flag
        //因为直接如果没有相交，最后pA pB会同时到达null
        return pA;
    }
}
