## 第一版 暴力 --- 但是和标准题解是一致的

执行结果：
通过
显示详情
添加备注

执行用时：
7 ms
, 在所有 Java 提交中击败了
86.91%
的用户
内存消耗：
41.6 MB
, 在所有 Java 提交中击败了
31.61%
的用户
通过测试用例：
85 / 85

class Solution {
    public int minDeletionSize(String[] strs) {
        int colLen = strs[0].length();
        int rowLen = strs.length;
        int deletedCol = 0;
        for(int col = 0; col < colLen; col++){
            for(int row = 1; row < rowLen; row++){
                if(strs[row].charAt(col) < strs[row - 1].charAt(col)){
                    deletedCol++;
                    break;
                }
            }
        }
        return deletedCol;
    }
}
