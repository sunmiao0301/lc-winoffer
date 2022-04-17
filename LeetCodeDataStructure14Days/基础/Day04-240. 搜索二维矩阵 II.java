## 第一版 转换思路 从右上角开始 -- 和题解思路一致

执行结果：
通过
显示详情
添加备注

执行用时：
5 ms
, 在所有 Java 提交中击败了
95.04%
的用户
内存消耗：
47 MB
, 在所有 Java 提交中击败了
64.35%
的用户
通过测试用例：
129 / 129

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //从右上角开始出发
        //然后只允许向左 和 下 走
        //超界就结束
        int row = 0;
        int col = matrix[0].length - 1;
        while(row < matrix.length && col > -1){
            if(matrix[row][col] == target) return true;
            else if(matrix[row][col] > target) col--;
            else row++;
        }
        return false;
    }
}

