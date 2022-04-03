## 第一版 我这个应该是介于 m * n 和 m + n 的空间复杂度的思路
执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
54.27%
的用户
内存消耗：
43.2 MB
, 在所有 Java 提交中击败了
10.00%
的用户
通过测试用例：
164 / 164
class Solution {
    public void setZeroes(int[][] matrix) {
        //一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
        //一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
        //你能想出一个仅使用常量空间的解决方案吗？
        List<Integer> list = new LinkedList<>();
        // m * n
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    list.add(i);
                    list.add(j);
                }
            }
        }
        for(int i = 0; i < list.size(); i++){
            //row = list.get(i); 
            int row = list.get(i); 
            for(int col = 0; col < n; col++){
                matrix[row][col] = 0;
            }
            i++;
            int col = list.get(i);
            for(row = 0; row < m; row++){
                matrix[row][col] = 0;
            }
        }
    }
}

## 题解第二版 为空间复杂度 m + n 的解法：
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        //空间复杂度：O(m+n)O(m+n)，其中 mm 是矩阵的行数，nn 是矩阵的列数。我们需要分别记录每一行或每一列是否有零出现。
        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];
        //
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = col[j] = true;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (row[i] || col[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}

## 题解 为空间复杂度为 1 的解法：

时间复杂度：O(n∗m)
空间复杂度：O(1)
题解分析链接 ： "https://leetcode-cn.com/problems/set-matrix-zeroes/solution/xiang-jie-fen-san-bu-de-o1-kong-jian-jie-dbxd/"
class Solution {
    public void setZeroes(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        // 1. 扫描「首行」和「首列」记录「首行」和「首列」是否该被置零
        boolean r0 = false, c0 = false;
        for (int i = 0; i < m; i++) {
            if (mat[i][0] == 0) {
                r0 = true;
                break;
            }
        }
        for (int j = 0; j < n; j++) {
            if (mat[0][j] == 0) {
                c0 = true;
                break;
            }
        }
        // 2.1 扫描「非首行首列」的位置，如果发现零，将需要置零的信息存储到该行的「最左方」和「最上方」的格子内
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (mat[i][j] == 0) mat[i][0] = mat[0][j] = 0;
            }
        }
        // 2.2 根据刚刚记录在「最左方」和「最上方」格子内的置零信息，进行「非首行首列」置零
        for (int j = 1; j < n; j++) {
            if (mat[0][j] == 0) {
                for (int i = 1; i < m; i++) mat[i][j] = 0;
            }
        }
        for (int i = 1; i < m; i++) {
            if (mat[i][0] == 0) Arrays.fill(mat[i], 0);
        }
        // 3. 根据最开始记录的「首行」和「首列」信息，进行「首行首列」置零
        if (r0) for (int i = 0; i < m; i++) mat[i][0] = 0;
        if (c0) Arrays.fill(mat[0], 0);
    }
}
