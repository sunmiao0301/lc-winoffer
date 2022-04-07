## 第一版 但是错在了如下测试样例上： -- 比较疑惑如果机器人初始点就是1 那机器人在哪？ 这个测试样例有点无语了
执行结果：
解答错误
显示详情
添加备注

通过测试用例：
36 / 41
输入：
[[1]]
输出：
1
预期结果：
0
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //网格中有障碍物 -- obstacleGrid数组是作为一个检查数组，用于写if
        // 1 <= m, n <= 100
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        //initial
        dp[0][0] = 1;
        for(int i = 1; i < m; i++){//竖直
            if(obstacleGrid[i][0] == 1){
                dp[i][0] = 0;
            }
            else{
                dp[i][0] = dp[i - 1][0];
            }
        }
        for(int j = 1; j < n; j++){//竖直
            if(obstacleGrid[0][j] == 1){
                dp[0][j] = 0;
            }
            else{
                dp[0][j] = dp[0][j - 1];
            }
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }
                else{
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}

## 第二版 对第一版补充了一个样例 通过
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
39.6 MB
, 在所有 Java 提交中击败了
35.17%
的用户
通过测试用例：
41 / 41
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //网格中有障碍物 -- obstacleGrid数组是作为一个检查数组，用于写if
        // 1 <= m, n <= 100
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        //initial
        if(obstacleGrid[0][0] == 1)return 0;
        dp[0][0] = 1;
        for(int i = 1; i < m; i++){//竖直
            if(obstacleGrid[i][0] == 1){
                dp[i][0] = 0;
            }
            else{
                dp[i][0] = dp[i - 1][0];
            }
        }
        for(int j = 1; j < n; j++){//竖直
            if(obstacleGrid[0][j] == 1){
                dp[0][j] = 0;
            }
            else{
                dp[0][j] = dp[0][j - 1];
            }
        }
        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                }
                else{
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}

## 题解
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length, m = obstacleGrid[0].length;
        int[] f = new int[m];

        f[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (obstacleGrid[i][j] == 1) {
                    f[j] = 0;
                    continue;
                }
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    f[j] += f[j - 1];
                }
            }
        }
        
        return f[m - 1];
    }
}
