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
(2)寻找链表中点 + 链表逆序 + 合并链表：
(3)递归 ：


## 第二版，自己写的如下，还是写的很艰难的。
## 思路是寻找链表中点 + 链表逆序 + 合并链表：

执行结果：
通过
显示详情
添加备注

执行用时：
2 ms
, 在所有 Java 提交中击败了
42.25%
的用户
内存消耗：
44.4 MB
, 在所有 Java 提交中击败了
9.35%
的用户
通过测试用例：
12 / 12

class Solution {
    public void reorderList(ListNode head) {
        // 1 2 3 4 5 --> 1 2 3 5 4 --> 1 5 2 4 3
        // 1 2 3 4   --> 1 2 4 3   --> 1 4 2 3
        if(head.next == null)return;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rev = slow.next;
        slow.next = reverseList(rev);//反转链表 并且 连接上去

        ListNode slowCur = head;
        ListNode slowNext = head.next;
        ListNode fastCur = slow.next;
        ListNode fastNext = slow.next.next;

        while(slowCur != null && fastCur != null){
            slowCur.next = fastCur;
            slowCur = slowNext;
            if(slowNext == null || slowNext == slow)
                slowNext = null;
            else{
                slowNext = slowNext.next;
            }

            // if(slowCur == null && fastCur == null) break;
            
            fastCur.next = slowCur;
            fastCur = fastNext;
            if(fastNext != null)
                fastNext = fastNext.next;
        }

        if(slowCur != null){
            slowCur.next = null;
        }
    }
    public ListNode reverseList(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode ret = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return ret;
    }
}

## 标准题解的 寻找链表中点 + 链表逆序 + 合并链表 如下：
## 题解写的非常之聪明 -- 通过 mid.next = null; 直接将链表一分为二，后面就能少考虑很多东西。

执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
99.99%
的用户
内存消耗：
44.3 MB
, 在所有 Java 提交中击败了
12.91%
的用户
通过测试用例：
12 / 12

class Solution {
    public void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode mid = middleNode(head);
        ListNode l1 = head;
        ListNode l2 = mid.next;
        
        mid.next = null;//聪明的一步操作
        
        l2 = reverseList(l2);
        mergeList(l1, l2);
    }

    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    public void mergeList(ListNode l1, ListNode l2) {
        ListNode l1_tmp;
        ListNode l2_tmp;
        while (l1 != null && l2 != null) {
            l1_tmp = l1.next;
            l2_tmp = l2.next;

            l1.next = l2;
            l1 = l1_tmp;

            l2.next = l1;
            l2 = l2_tmp;
        }
    }
}
