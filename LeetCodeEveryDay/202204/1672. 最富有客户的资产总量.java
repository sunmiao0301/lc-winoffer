## 第一版 无难度

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
40.9 MB
, 在所有 Java 提交中击败了
33.33%
的用户
通过测试用例：
34 / 34

class Solution {
    public int maximumWealth(int[][] accounts) {
        //横着到倒数第二个
        for(int j = 1; j < accounts[0].length - 1; j++){
            for(int i = 0; i < accounts.length; i++){
                accounts[i][j] += accounts[i][j - 1];
            }
        }
        int max = 0;
        for(int i = 0; i < accounts.length; i++){
            if(accounts[0].length != 1)
                accounts[i][accounts[0].length - 1] += accounts[i][accounts[0].length - 2];
            if(accounts[i][accounts[0].length - 1] > max){
                max = accounts[i][accounts[0].length - 1];
            }
        }
        return max;
    }
}
