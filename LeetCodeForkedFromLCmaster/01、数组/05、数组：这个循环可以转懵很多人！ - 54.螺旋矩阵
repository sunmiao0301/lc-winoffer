//第一版 一遍过 我是先做的59螺旋矩阵II 再做的这道题 所以这次是模仿K神的思路写法：

执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
36.3 MB
, 在所有 Java 提交中击败了
89.28%
的用户

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
    int m = matrix.length;
    int n = matrix[0].length;
    List<Integer> ret = new ArrayList<>();
    int row = 0, col = 0, l2r = n, u2d = m, r2l = -1, d2u = 0;
    int i = 0;
    while(i < m * n){
        for(; col < l2r; col++, i++){
            ret.add(matrix[row][col]);
        }
        col--;
        row++;
        l2r--;
        if(i >= m * n)break;
        for(; row < u2d; row++, i++){
            ret.add(matrix[row][col]);
        }
        row--;
        col--;
        u2d--;
        if(i >= m * n)break;
        for(; col > r2l; col--, i++){
            ret.add(matrix[row][col]);
        }
        col++;
        row--;
        r2l++;
        for(; row > d2u; row--, i++){
            ret.add(matrix[row][col]);
        }
        row++;
        col++;
        d2u++;
    }
    return ret;
    }
}

