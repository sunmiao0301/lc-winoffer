## 第一版 没啥含金量

执行结果：
通过
显示详情
添加备注

执行用时：
8 ms
, 在所有 Java 提交中击败了
36.69%
的用户
内存消耗：
53.1 MB
, 在所有 Java 提交中击败了
82.56%
的用户
通过测试用例：
86 / 86

class Solution {
    public boolean isPalindrome(ListNode head) {
        //进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
        List<Integer> list = new ArrayList<>();
        //int i = 0;
        while(head != null){
            list.add(head.val);
            head = head.next;
        }
        int l = 0;
        int r = list.size() - 1;
        while(l < r){
            if(list.get(l) != list.get(r)){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}

## 因为之前看过题解 所以我知道还有两种写法 我想都自己写一下试试

## 第二版 递归

## 第三版 空间复杂度为 o(1)
