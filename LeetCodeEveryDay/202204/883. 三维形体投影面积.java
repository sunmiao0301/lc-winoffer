## 第一版 忘记了 0 <= grid[i][j] <= 50 可以为 0 导致出错

执行结果：
解答错误
显示详情
添加备注

通过测试用例：
24 / 90
输入：
[[1,0],[0,2]]
输出：
10
预期结果：
8

class Solution {
    public int projectionArea(int[][] grid) {
        //n == grid.length == grid[i].length
        //底部一定是正方体
        // x--->
        // [][]
        // [][]
        // 分析一下得到了思路：遍历得到x上的最大值总和，以及y上的最大值总和，以及底部为n2
        int n = grid.length;
        int xy = n * n;
        int xz = 0;
        int yz = 0;
        for(int i = 0; i < n; i++){
            int maxInJ = 0;
            for(int j = 0; j < n; j++){
                maxInJ = Math.max(maxInJ, grid[j][i]);
            }
            xz += maxInJ;
        }
        for(int i = 0; i < n; i++){
            int maxInJ = 0;
            for(int j = 0; j < n; j++){
                maxInJ = Math.max(maxInJ, grid[i][j]);
            }
            yz += maxInJ;
        }
        return xz + yz + xy;
    }
}

## 第二版 通过 但是时间不是最好

执行结果：
通过
显示详情
添加备注

执行用时：
2 ms
, 在所有 Java 提交中击败了
72.67%
的用户
内存消耗：
41.2 MB
, 在所有 Java 提交中击败了
23.00%
的用户
通过测试用例：
90 / 90

class Solution {
    public int projectionArea(int[][] grid) {
        //n == grid.length == grid[i].length
        //底部一定是正方体
        // x--->
        // [][]
        // [][]
        // 分析一下得到了思路：遍历得到x上的最大值总和，以及y上的最大值总和，以及底部为n2
        int n = grid.length;
        int xy = n * n;
        int xz = 0;
        int yz = 0;
        for(int i = 0; i < n; i++){
            int maxInJ = 0;
            for(int j = 0; j < n; j++){
                maxInJ = Math.max(maxInJ, grid[j][i]);
                if(grid[j][i] == 0){
                    xy--;
                }
            }
            xz += maxInJ;
        }
        for(int i = 0; i < n; i++){
            int maxInJ = 0;
            for(int j = 0; j < n; j++){
                maxInJ = Math.max(maxInJ, grid[i][j]);
            }
            yz += maxInJ;
        }
        return xz + yz + xy;
    }
}

## 题解 当笨比了 直接合二为一就行了 化 2 * n2 为 n2
class Solution {
    public int projectionArea(int[][] grid) {
        int n = grid.length;
        int xyArea = 0, yzArea = 0, zxArea = 0;
        for (int i = 0; i < n; i++) {
            int yzHeight = 0, zxHeight = 0;
            for (int j = 0; j < n; j++) {
                xyArea += grid[i][j] > 0 ? 1 : 0;
                yzHeight = Math.max(yzHeight, grid[i][j]);
                zxHeight = Math.max(zxHeight, grid[j][i]);
            }
            yzArea += yzHeight;
            zxArea += zxHeight;
        }
        return xyArea + yzArea + zxArea;
    }
}
