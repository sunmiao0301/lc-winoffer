## 看题解 但是没看代码 然后自己写出来的 但是速度却很一般

执行结果：
通过
显示详情
添加备注

执行用时：
197 ms
, 在所有 Java 提交中击败了
29.67%
的用户
内存消耗：
45.8 MB
, 在所有 Java 提交中击败了
10.64%
的用户
通过测试用例：
180 / 180

注意！
boolean默认值是false
Boolean默认值是null

class Solution {
    public String longestPalindrome(String s) {
    //     {
    //       | 00 01 02 03 04
    //       10 | 11 12 13 14
    //       20 21 | 22 23 24
    //       30 31 32 | 33 34
    //       40 41 42 43 | 44
    //     }
    //根据我列举的情况 我们可以有两种遍历方式
    //第一种是 00 - 01 - 11 - 02 - 12 - 22 - ...(02是依赖11的 03是依赖12的)
    //第二种是 44 - 33 - 34 - 22 - 23 - 24 - ...
    int n = s.length();
    boolean[][] dp = new boolean[n][n];
    int res = 0;
    int left = 0;
    int right = 0;
    for(int i = 0; i < n; i++){//两个都遍历0 ~ n-1 也是可以的（遇到i > j就continue，但是这样写更聪明）
        for(int j = 0; j <= i; j++){
            if(i == j){
                dp[j][i] = true;
            }
            else if(i - j == 1){
                dp[j][i] = s.charAt(i) == s.charAt(j);
                if(dp[j][i] == true && i - j + 1 > res){
                    res = i - j + 1;
                    left = j;
                    right = i;
                }   
            }
            else{
                dp[j][i] = dp[j + 1][i - 1] && (s.charAt(i) == s.charAt(j));
                if(dp[j][i] == true && i - j + 1 > res){
                    res = i - j + 1;
                    left = j;
                    right = i;                    
                }   
            }
        }
    }
    return s.substring(left, right + 1);
    }
}
