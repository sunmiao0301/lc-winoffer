第一版 超时
执行结果：
超出时间限制 —— 因为做了大量的重复计算 —— 可以通过动态规划来解决
最后执行的输入：
45
class Solution {
    public int climbStairs(int n) {
        if(n == 1)
            return 1;
        if(n == 2)
            return 2;
        else
            return climbStairs(n - 1) + climbStairs(n - 2);
    }
}

第二版
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
35.1 MB
, 在所有 Java 提交中击败了
63.38%
的用户
通过测试用例：
45 / 45
class Solution {
    public int climbStairs(int n) {
        //动态规划
        //给定 n 是一个正整数
        if(n == 1)return 1;
        int[] climbPaths = new int[n];
        climbPaths[0] = 1;
        climbPaths[1] = 2;
        for(int i = 2; i < n; i++){
            climbPaths[i] = climbPaths[i - 2] + climbPaths[i - 1];
        }
        return climbPaths[n - 1];
    }
}

####
实际上只用到三个数 所以空间复杂度还能再优化一下
class Solution {
    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q; 
            q = r; 
            r = p + q;
        }
        return r;
    }
}
