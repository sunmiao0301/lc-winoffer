## 第一版 通过 但是还可以更优雅

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
39.5 MB
, 在所有 Java 提交中击败了
25.89%
的用户
通过测试用例：
20 / 20

class Solution {
    public int[][] generateMatrix(int n) {
        int left = 0;
        int right = n - 1;
        int up = 0;
        int down = n - 1;
        int i = 1;
        int len = n * n;
        int[][] res = new int[n][n];
        while(i <= len){
            for(int l = left; l <= right; l++){
                res[up][l] = i;
                i++;
            }
            up++;
            for(int u = up; u <= down; u++){
                res[u][right] = i;
                i++;
            }
            right--;
            for(int r = right; r >= left; r--){
                res[down][r] = i;
                i++;
            }
            down--;
            for(int d = down; d >= up; d--){
                res[d][left] = i;
                i++;
            }
            left++;
        }
        return res;
    }
}

## 题解 更优雅

class Solution {
    public int[][] generateMatrix(int n) {
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while(num <= tar){
            for(int i = l; i <= r; i++) mat[t][i] = num++; // left to right.
            t++;
            for(int i = t; i <= b; i++) mat[i][r] = num++; // top to bottom.
            r--;
            for(int i = r; i >= l; i--) mat[b][i] = num++; // right to left.
            b--;
            for(int i = b; i >= t; i--) mat[i][l] = num++; // bottom to top.
            l++;
        }
        return mat;
    }
}
