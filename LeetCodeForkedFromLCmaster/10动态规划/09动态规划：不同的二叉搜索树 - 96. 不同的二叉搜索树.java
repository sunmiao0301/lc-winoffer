## 第一版 看了题解思路 然后自己写出的
## 这题的思路不是很明显 如果不看题解 不太好想到 --- 'https://leetcode-cn.com/problems/unique-binary-search-trees/solution/hua-jie-suan-fa-96-bu-tong-de-er-cha-sou-suo-shu-b/
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
37.9 MB
, 在所有 Java 提交中击败了
71.82%
的用户
通过测试用例：
19 / 19
class Solution {
    public int numTrees(int n) {
        //二叉搜索树
        //找规律？
        //dp[i]指的是i个节点组成的树的种数
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i < n + 1; i++){
            int res = 0;//dp[i]的树种类数
            int tmp = i - 1;//除去根节点 剩余的可支配到左右子树的节点数
            for(int j = 0; j <= tmp; j++){
                res += dp[j] * dp[tmp - j];
            }
            dp[i] = res;
        }
        return dp[n];
    }
}

## 题解：
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        
        for(int i = 2; i < n + 1; i++)
            for(int j = 1; j < i + 1; j++) 
                dp[i] += dp[j-1] * dp[i-j];
        
        return dp[n];
    }
}
