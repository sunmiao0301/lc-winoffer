## 第一版 看了题解后写出来的最基本的一版动态规划
执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
75.98%
的用户
内存消耗：
38.5 MB
, 在所有 Java 提交中击败了
6.93%
的用户
通过测试用例：
50 / 50
class Solution {
    public int integerBreak(int n) {
        //拆分为 k 个正整数的和（ k >= 2 )
        //dp[i]表示i拆分得到的最大值
        //注意dp[n + 1]
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j < i; j++){
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));//dp[i]之所以存在，是因为j导致循环很多次，第二次的时候不能盲目覆盖j的第一轮得到的dp[i]
            }
        }
        return dp[n];
    }
}

## 题解 之 贪心 -- 此外还有其他方法：'https://leetcode-cn.com/problems/integer-break/solution/zheng-shu-chai-fen-by-leetcode-solution/'
本题也可以用贪心，每次拆成n个3，如果剩下是4，则保留4，然后相乘，但是这个结论需要数学证明其合理性！

我没有证明，而是直接用了结论。感兴趣的同学可以自己再去研究研究数学证明哈。

给出我的C++代码如下：

class Solution {
public:
    int integerBreak(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        int result = 1;
        while (n > 4) {
            result *= 3;
            n -= 3;
        }
        result *= n;
        return result;
    }
};
时间复杂度：$O(n)
空间复杂度：$O(1)
