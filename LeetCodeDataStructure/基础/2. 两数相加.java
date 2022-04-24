## 第一版 通过 注意其与 两数相乘 的区别 以及注意事项

执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
41.1 MB
, 在所有 Java 提交中击败了
82.36%
的用户
通过测试用例：
1568 / 1568
炫耀一下:

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int a = 0;
        int b = 0;
        int carry = 0;
        int tmp = 0;
        ListNode sentinel = new ListNode(1);
        ListNode p = sentinel;
        while(l1 != null || l2 != null || carry != 0){
            /**
             * 注意a b tmp需在此处重新置0
             */
            a = 0;
            b = 0;
            tmp = 0;
            if(l1 != null){//注意判断
                a = l1.val;
                l1 = l1.next;// 注意在这里加
            }
            if(l2 != null){//注意判断
                b = l2.val;
                l2 = l2.next;// 注意在这里加
            }
            tmp = a + b + carry;
            p.next = new ListNode(tmp % 10);
            p = p.next;
            carry = tmp / 10;
        }
        return sentinel.next;
    }
}
