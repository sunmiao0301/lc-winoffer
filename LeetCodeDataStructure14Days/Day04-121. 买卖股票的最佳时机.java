## 
执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
57.9 MB
, 在所有 Java 提交中击败了
13.40%
的用户
通过测试用例：
211 / 211
class Solution {
    public int maxProfit(int[] prices) {
        //dp[]到某天为止，最大的利润
        //int[] dp = new int[prices.length];
        //动态规划 前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
        int min = prices[0];
        int max = 0;
        for(int i = 0; i < prices.length; i++){
            //dp[i] = Math.min(prices[i] - min, max);
            if(prices[i] < min){
                min = prices[i];
            }
            if(max < prices[i] - min)
                max = prices[i] - min;
        }
        return max;
    }
}
