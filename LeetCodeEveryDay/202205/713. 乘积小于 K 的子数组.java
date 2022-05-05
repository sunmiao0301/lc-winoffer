## 第一版 通过 但是速度很慢 -- 做了很多重复的乘法运算，可以考虑往这个方向优化

执行结果：
通过
显示详情
添加备注

执行用时：
865 ms
, 在所有 Java 提交中击败了
15.97%
的用户
内存消耗：
47.9 MB
, 在所有 Java 提交中击败了
48.64%
的用户
通过测试用例：
97 / 97

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        //感觉是滑动窗口来做 两层for循环 然后一旦大于就结束
        //连续子数组 -- 滑动窗口
        int res = 0;
        int len = nums.length;
        for(int i = 0; i < len; i++){
            int sum = nums[i];
            if(sum < k) res++;
            for(int j = i + 1; j < len; j++){
                sum *= nums[j];
                if(sum < k) res++;
                else{
                    break;
                }
            }
        }
        return res;
    }
}

## 瞟了一眼题解 居然可以在 o(n) 的时间复杂度的情况下，用滑动窗口解决？
## 看了题解 果然是优化了我所做的大量重复的乘法运算
## 打个比方，如果数据是 1 2 3 4 5
## 那么我的思路是 看 1 是否小于 k 如果是，则再看 1 * 2 --> 1 * 2 * 3 --> 1 * 2 * 3 * 4 --> 1 * 2 * 3 * 4 * 5 以此类推 
## 然后再进行第二轮（滑动窗口的左侧右移 --> 2 * 3 --> 2 * 3 * 4 --> 2 * 3 * 4 * 5 ）
## 而题解所做的优化就在于： 如果 1 * 2 * 3 * 4 满足，则 2 * 3 * 4 也一定满足 所以我们第二轮直接从 2 * 3 * 4 开始继续统计。 ———— 这句话看似简单，但是实现起来却需要考虑不少（可能是我写的不好）

## 第二版 但是忽略了 k 很小（如 k = 0的时候，我这版将无法处理）

执行结果：
解答错误
显示详情
添加备注

通过测试用例：
94 / 97
输入：
[1,2,3]
0
输出：
-6
预期结果：
0

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int res = 0;
        int sum = 1;
        int len = nums.length;
        int right = -1;
        // int left = 0;

        for(int left = 0; left < len; left++){

            while(sum < k){

                right++;

                if(right == len){
                    while(left < len){
                        res += right - left;
                        left++;
                    }
                    return res;
                }

                sum *= nums[right];

            }

            res += right - left;
            sum /= nums[left];
        
        }
        return res;
    }
}

## 第三版 修正 通过

执行结果：
通过
显示详情
添加备注

执行用时：
5 ms
, 在所有 Java 提交中击败了
30.78%
的用户
内存消耗：
47.9 MB
, 在所有 Java 提交中击败了
45.37%
的用户
通过测试用例：
97 / 97

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int res = 0;
        int sum = 1;
        int len = nums.length;
        int right = -1;

        for(int left = 0; left < len; left++){

            while(sum < k){

                right++;

                if(right == len){
                    while(left < len){
                        res += right - left;
                        left++;
                    }
                    return res;
                }

                sum *= nums[right];

            }

            res += Math.max(right - left, 0); // 加了个这个 避免了 k = 0 的影响
            sum /= nums[left];
        
        }
        return res;
    }
}

## 题解 速度和我的一样 但是写的简单多了

执行结果：
通过
显示详情
添加备注

执行用时：
5 ms
, 在所有 Java 提交中击败了
30.78%
的用户
内存消耗：
47.7 MB
, 在所有 Java 提交中击败了
77.25%
的用户
通过测试用例：
97 / 97

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int n = nums.length, ret = 0;
        int prod = 1, i = 0;
        for (int j = 0; j < n; j++) {
            prod *= nums[j];
            while (i <= j && prod >= k) {
                prod /= nums[i];
                i++;
            }
            ret += j - i + 1;
        }
        return ret;
    }
}
