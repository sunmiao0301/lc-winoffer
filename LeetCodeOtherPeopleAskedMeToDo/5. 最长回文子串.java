## 看题解 但是没看代码 然后自己写出来的 但是速度很一般

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

## 力扣官方题解的速度也很慢 所以这里摘录一个评论区看到的速度很快的

执行结果：
通过
显示详情
添加备注

执行用时：
2 ms
, 在所有 Java 提交中击败了
99.84%
的用户
内存消耗：
41.1 MB
, 在所有 Java 提交中击败了
88.50%
的用户
通过测试用例：
180 / 180

class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
//         保存起始位置，测试了用数组似乎能比全局变量稍快一点
        int[] range = new int[2];
        char[] str = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
//             把回文看成中间的部分全是同一字符，左右部分相对称
//             找到下一个与当前字符不同的字符
            i = findLongest(str, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }
    
    public static int findLongest(char[] str, int low, int[] range) {
//         查找中间部分
        int high = low;
        while (high < str.length - 1 && str[high + 1] == str[low]) {
            high++;
        }
//         定位中间部分的最后一个字符
        int ans = high;
//         从中间向左右扩散
        while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
            low--;
            high++;
        }
//         记录最大长度
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        return ans;
    }
}
