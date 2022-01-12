第一版 看了题解自己写的
执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
51.4 MB
, 在所有 Java 提交中击败了
33.82%
的用户
通过测试用例：
211 / 211
class Solution {
    public int maxProfit(int[] prices) {
        //动态规划 分析一下
        /** 
        但是题解给的思路感觉更清晰，所以直接不考虑什么的动态规划了，思路就是
        维护一个历史最低点minPrice，一个最佳卖出点的利润
        然后遍历每一天
        如果今天是目前的历史最低点，当天就是0，因为当天肯定不能卖，之前肯定有更好的卖出时间点maxProfit
        如果今天不是新的历史最低点，就用当前值减去之前的历史最低点，得到一个值，如果这个值比maxProfit更好，就更新maxProfit
        */
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        int len = prices.length;
        for(int i = 0; i < len; i++){
            if(prices[i] < minPrice){
                minPrice = prices[i];
            }
            else{
                maxProfit = prices[i] - minPrice > maxProfit ? prices[i] - minPrice : maxProfit;
            }
        }
        return maxProfit;
    }
}

####
另一种解释： 
可以看做一种动态规划，只不过对空间复杂度进行了优化。
考虑每次如何获取最大收益？第i天的最大收益只需要知道前i天的最低点就可以算出来了。而第i天以前（包括第i天）的最低点和i-1天的最低点有关，至此我们的动态方程就出来了。

dp[i] = min(dp[i-1],prices[i])
其中dp[0]=prices[0],然后动态计算之后的就可以了。 
得到了前i天的最低点以后，只需要维护一个max用来保存最大收益就可以了。 
这个时候是空间复杂度O（n）的动态规划，代码如下：

        //dp[i]表示截止到i，价格的最低点是多少   dp[i]=min(dp[i-1],nums[i])
        int max = 0;
        int[] dp = new int[prices.length];
        dp[0] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = (dp[i - 1] < prices[i]) ? dp[i - 1] : prices[i];
            max = (prices[i] - dp[i]) > max ? prices[i] - dp[i] : max;
        }
        return max;

接着考虑优化空间，仔细观察动态规划的辅助数组，其每一次只用到了dp[i-1]这一个空间，因此可以把数组改成单个变量dp来存储截止到第i天的价格最低点。

一言，就是：
未优化的情况下，动态规划维护的数组dp[i] 是 到第i天为止，股票的最低的价格
优化后的情况下，不必维护每一天，而只需要维护最低点的一天。
