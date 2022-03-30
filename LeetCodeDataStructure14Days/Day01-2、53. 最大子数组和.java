//第一版 动态规划 可以优化（不需数组） —— 但是这样看着清楚
class Solution {
    public int maxSubArray(int[] nums) {
        //dp[] -- 定义为: 包含当前i位置的数的情况的最大数组和的值 || 前n个数的最大和的连续子数字的和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];

        for(int i = 1; i < nums.length; i++){
            if(dp[i - 1] >= 0){//if(dp[i - 1] + nums[i] >= nums[i])
                dp[i] = dp[i - 1] + nums[i];
            }
            else{
                dp[i] = nums[i];
            }
            if(dp[i] > max)
                max = dp[i];
        }
        return max;
    }
}
