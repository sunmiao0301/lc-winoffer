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

##
