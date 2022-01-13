第一版
执行结果：
通过
执行用时：
2 ms
, 在所有 Java 提交中击败了
46.16%
的用户
内存消耗：
47.3 MB
, 在所有 Java 提交中击败了
79.08%
的用户
通过测试用例：
209 / 209
class Solution {
    public int maxSubArray(int[] nums) {
        //进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
        //对于动态规划而言
        //通过这一题，我想到一个很关键的点，就是动态规划数组内的每个点的意义
        //我们必须考虑清楚，并且应该每个点只和前面有限个点有关，否则就不是动态规划了
        //所以在这题，我们需要想到：
        //到第3个点的时候，就不应该再考虑第1个点的值了
        //所以本题动态规划数组dp[i]的值意义是
        //当连续子数组的最后一个值是当前位置的数的时候，最大的数组值的和
        //那么此时应当只考虑当前值和前一个位置的值，
        int len = nums.length;
        int max = nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];
        for(int i = 1; i < len; i++){
            if(dp[i - 1] > 0){
                dp[i] = dp[i - 1] + nums[i];
            }
            else
                dp[i] = nums[i];
            if(dp[i] > max) 
                max = dp[i];
        }
        return max;
    }
}

第二版 对空间进行优化
class Solution {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int pre = nums[0];
        int max = nums[0];
        //int[] dp = new int[len];
        for(int i = 1; i < len; i++){
            if(pre > 0){
                pre += nums[i];
            }
            else
                pre = nums[i];
            if(pre > max) 
                max = pre;
        }
        return max;
    }
}

####
题解 和我第二版一样的思路 但是效果却好得多
执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
48.7 MB
, 在所有 Java 提交中击败了
18.72%
的用户
通过测试用例：
209 / 209
class Solution {
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }
}

//进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
#### 题解 未学
class Solution {
    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public int maxSubArray(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
}
  
题外话
「方法二」相较于「方法一」来说，时间复杂度相同，但是因为使用了递归，并且维护了四个信息的结构体，运行的时间略长，空间复杂度也不如方法一优秀，而且难以理解。那么这种方法存在的意义是什么呢？

对于这道题而言，确实是如此的。但是仔细观察「方法二」，它不仅可以解决区间 [0, n-1][0,n−1]，还可以用于解决任意的子区间 [l,r][l,r] 的问题。如果我们把 [0, n-1][0,n−1] 分治下去出现的所有子区间的信息都用堆式存储的方式记忆化下来，即建成一颗真正的树之后，我们就可以在 O(\log n)O(logn) 的时间内求到任意区间内的答案，我们甚至可以修改序列中的值，做一些简单的维护，之后仍然可以在 O(\log n)O(logn) 的时间内求到任意区间内的答案，对于大规模查询的情况下，这种方法的优势便体现了出来。这棵树就是上文提及的一种神奇的数据结构——线段树。
