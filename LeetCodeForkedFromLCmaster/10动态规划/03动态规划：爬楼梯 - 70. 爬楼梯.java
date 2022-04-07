## 第一版
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
38.4 MB
, 在所有 Java 提交中击败了
8.12%
的用户
通过测试用例：
45 / 45
class Solution {
    public int climbStairs(int n) {
        //dp[i]表示到第i层共有dp[i]种爬法,dp[i] = dp[i-1] + dp[i-2];
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
