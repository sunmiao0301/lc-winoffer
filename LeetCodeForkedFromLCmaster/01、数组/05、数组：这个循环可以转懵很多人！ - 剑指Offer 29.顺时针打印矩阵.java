2nd
执行结果：
执行出错
WARNING: A command line option has enabled the Security Manager
WARNING: The Security Manager is deprecated and will be removed in a future release
java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
  at line 6, Solution.spiralOrder
  at line 57, __DriverSolution__.__helper__
  at line 82, __Driver__.main
最后执行的输入：
[]

class Solution {
    public int[] spiralOrder(int[][] matrix) {
        //可以维护一个visited数组，也可以直接用四个边界值限制一下。
        int row = 0;
        int col = 0;
        int colBoundaryRight = matrix[0].length;
        int rowBoundaryBottom = matrix.length;
        int colBoundaryLeft = -1;
        int rowBoundaryTop = 0;
        int i = 0;
        int iMax = colBoundaryRight * rowBoundaryBottom;
        int[] res = new int[iMax];
        while(i < iMax){
            while(col < colBoundaryRight){
                res[i] = matrix[row][col];
                col++;
                i++;
            }
            col--;
            row++;
            colBoundaryRight--;
            while(row < rowBoundaryBottom){
                res[i] = matrix[row][col];
                row++;
                i++;
                if(i >= iMax)
                    break;
            }
            if(i >= iMax)
                break;
            row--;
            col--;
            rowBoundaryBottom--;
            while(col > colBoundaryLeft){
                res[i] = matrix[row][col];
                col--;
                i++;
            }
            col++;
            row--;
            colBoundaryLeft++;
            while(row > rowBoundaryTop){
                res[i] = matrix[row][col];
                row--;
                i++;
            if(i >= iMax)
                 break;
            }
            if(i >= iMax)
                 break;
            row++;
            col++;
            rowBoundaryTop++;
        }
    return res;
    }
}

2rd 第二版
加上：
if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
    return new int[0];
}
即可通过如下

执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
97.50%
的用户
内存消耗：
43.8 MB
, 在所有 Java 提交中击败了
5.27%
的用户
通过测试用例：
27 / 27

同 题54 - 螺旋矩阵 已做过
