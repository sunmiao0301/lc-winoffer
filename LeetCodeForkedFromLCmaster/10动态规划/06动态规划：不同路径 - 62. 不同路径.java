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
37.8 MB
, 在所有 Java 提交中击败了
78.24%
的用户
通过测试用例：
63 / 63
class Solution {
    public int uniquePaths(int m, int n) {
        //dp[i][j]表示到i,j处的可能的路径数
        int[][] dp = new int[m][n];
        //initial
        for(int i = 0; i < m; i++){
            dp[i][0] = 1;
        }
        for(int j = 0; j < n; j++){
            dp[0][j] = 1;
        }
        //dp -- 机器人每次只能向下或者向右移动一步。
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
