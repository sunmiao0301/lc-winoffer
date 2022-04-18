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
42.6 MB
, 在所有 Java 提交中击败了
6.60%
的用户
通过测试用例：
57 / 57
class Solution {
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        //1 <= m, n <= 100
        int m = mat.length;
        int n = mat[0].length;
        //int r, c;
        int[][] res = new int[r][c];
        if(r * c != m * n)
            return mat;
        for(int i = 0; i < r * c; i++){
            //r' = i / c;
            //c' = i % c;
            //m' = i / n;
            //n' = i % n;
            res[i / c][i % c] = mat[i / n][i % n];
        }
        return res;
    }
}
