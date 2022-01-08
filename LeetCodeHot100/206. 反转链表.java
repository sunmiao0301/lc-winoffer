第一版
已完成
执行用时：0 ms
输入
[1,2,3]
输出
[3]
预期结果
[3,2,1]
太久没写了 对链表节点和java引用都迷了 如下
class Solution {
    public ListNode reverseList(ListNode head) {
        //用两个指针就行了
        ListNode one = head;
        ListNode two = head.next;
        if(one == null || two == null) return head;
        one.next = null;
        ListNode three = two.next;
        if(three == null){
            two.next = one;
            return two;
        }
        while(three != null){//这里写错了
            two.next = one;
            one = two;
            two = three;
            three = three.next;
        }
        return two;
    }
}

第二版
睡前思考了一下 第二天醒来补上了
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
38.1 MB
, 在所有 Java 提交中击败了
69.71%
的用户
通过测试用例：
28 / 28
class Solution {
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode cur = head.next;
        ListNode pre = cur.next;
        head.next = null;
        while(pre != null){
            cur.next = head;
            head = cur;
            cur = pre;
            pre = pre.next;
        }
        cur.next = head;
        return cur;
    }
}

####
方法一：迭代
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;//其实还是维护了一个cur.next 只不过放在里面了
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}

####
方法二：递归
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

#### 2022/1/8 自己写的带注释的递归法
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
38.4 MB
, 在所有 Java 提交中击败了
15.69%
的用户
通过测试用例：
28 / 28
炫耀一下:
class Solution {
    ListNode ret;
    public ListNode reverseList(ListNode head) {
        //当只有一个节点或者没节点的时候
        if(head == null || head.next == null)return head;
        ListNode tmpCur = head;
        ListNode tmpPre = head.next;

        //for the last node in list
        if(tmpPre.next == null){
            tmpPre.next = tmpCur;
            ret = tmpPre;
            //不能在这里return 得放到外面?
        }
        //for mid node in list
        else{
            reverseList(tmpPre);
            tmpPre.next = tmpCur;
            //当链子节点数 >= 3的时候 最后的return靠这里
            if(tmpPre.next == head){
                tmpCur.next = null;
                return ret;
            }
        }
        //当只有两个节点
        tmpCur.next = null;
        return ret;
    }
}
