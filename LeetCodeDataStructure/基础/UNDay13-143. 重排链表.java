## 第一版 用了最简单粗暴的方法 --> 线性表存储

执行结果：
通过
显示详情
添加备注

执行用时：
3 ms
, 在所有 Java 提交中击败了
34.21%
的用户
内存消耗：
43.3 MB
, 在所有 Java 提交中击败了
92.45%
的用户
通过测试用例：
12 / 12
炫耀一下:

class Solution {
    public void reorderList(ListNode head) {
        //如果用list 确实索然无味
        //这题可以和回文链表一起做
        List<ListNode> list = new ArrayList<>();
        while(head != null){
            list.add(head);
            head = head.next;
        }
        int l = 0;
        int r = list.size() - 1;
        while(l < r){
            list.get(l).next = list.get(r);
            l++;
            if(l == r){
                list.get(r).next = null;
                break;
            }
            list.get(r).next = list.get(l);
            r--;
            list.get(l).next = null;
        }
    }
}

## 题解中 除了我的线性表方法 还有两种方法 --> （这与 234. 回文链表 是有异曲同工之妙的，也是三种方法
## 但是实际上递归相较于 集合存储的方法，是没有什么优势的，但是 "寻找链表中点 + 链表逆序 + 合并链表" 的方法是有好处的 -- 空间复杂度降低为 o(1)
(1)递归 ：
(2)寻找链表中点 + 链表逆序 + 合并链表：

