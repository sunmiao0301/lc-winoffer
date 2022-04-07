## 第一版 由于比较熟悉 直接就把动态规划的dp[]数组，优化为三个数字存储了
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
38.3 MB
, 在所有 Java 提交中击败了
16.49%
的用户
通过测试用例：
31 / 31
class Solution {
    public int fib(int n) {
        if(n == 0)return 0;
        if(n == 1)return 1;
        int pre = 0;
        int cur = 1;
        int next = 1;
        while(n > 2){
            pre = cur;
            cur = next;
            next = pre + cur;
            n--;
        }
        return next;
    }
}

## 
