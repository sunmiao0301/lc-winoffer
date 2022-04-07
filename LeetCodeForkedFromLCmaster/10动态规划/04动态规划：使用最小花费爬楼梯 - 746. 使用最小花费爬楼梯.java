## 第一版 思路不对
在测试样例上就会出错：
输入
[10,15,20]
[1,100,1,1,1,100,1,1,100,1]
输出
25
6
预期结果
15
6
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        //一旦你支付此费用，即可选择向上爬一个或者两个台阶。
        //你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
        
        //大致思考一下，每一步都选最小的即可
        //到达楼梯顶部。 -- i = n = cost.length
        int i = cost[0] >= cost[1] ? 1 : 0;
        int res = 0;
        while(i <= cost.length - 3){//i + 1 == cost.length || i + 2 == cost.length -- i >= len - 2
            res += cost[i];
            if(cost[i + 1] >= cost[i + 2]){
                //res += cost[i];
                i += 2;
            }
            else{
                i++;
            }
        }
        return res + cost[i];
    }
}

## 按照动态规划五步骤分析后，写出代码（https://programmercarl.com/0746.%E4%BD%BF%E7%94%A8%E6%9C%80%E5%B0%8F%E8%8A%B1%E8%B4%B9%E7%88%AC%E6%A5%BC%E6%A2%AF.html#%E6%80%BB%E7%BB%93
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
43.57%
的用户
通过测试用例：
283 / 283
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        //dp[i]描述的是到第i级台阶的最小花费值（并且包含这一步所需要的花费） -- 认为最后一步不要花费的话，就可以这样理解了
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for(int i = 2; i < cost.length; i++){
            dp[i] = Math.min(dp[i - 2] + cost[i], dp[i - 1] + cost[i]);
        }
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }
}
