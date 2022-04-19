## 第一版 用翻转代替旋转

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
40.2 MB
, 在所有 Java 提交中击败了
38.38%
的用户
通过测试用例：
21 / 21

class Solution {
    public void rotate(int[][] matrix) {
        //顺时针旋转90度 = 上下对称翻转 + 主对角线对称翻转
        int rowLen = matrix.length;
        int colLen = matrix[0].length;
        //首先上下翻转
        for(int i = 0; i < rowLen / 2; i++){ // 注意这里是 i < rowLen / 2; 而不是 i < rowLen / 2 + 1;
            for(int j = 0; j < colLen; j++){
                swap(i, j, rowLen - i - 1, j, matrix);
            }
        }
        //然后主对角线翻转
        for(int i = 0; i < rowLen; i++){
            for(int j = i + 1; j < colLen; j++){
                swap(i, j, j, i, matrix);
            }
        }
    }
    public void swap(int i1, int j1, int i2, int j2, int[][] matrix){
        int tmp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = tmp;
    }
}

## 除了这种，题解还给出了另外一种方法，但是时间、空间复杂度都一样："https://leetcode-cn.com/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
  
题解给出的方法二：原地旋转

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }
}

## p.s. 
顺时针旋转90：先沿对角线反转矩阵，再沿竖中轴线反转矩阵；
顺时针旋转180：先沿横中轴线反转矩阵，再沿竖中轴线反转矩阵；
顺时针旋转270：先沿对角线反转矩阵，再沿横中轴线反转矩阵；
