## 第一版 异或（用到题目的关键信息 -- 除了一个数外，其他数都是出现了 两次！）
class Solution {
    public int singleNumber(int[] nums) {
        //线性时间复杂度，你可以不使用额外空间来实现
        //感觉不用额外空间的话 就必须是遍历多次（但是还是n）
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            res = res ^ nums[i];
        }
        return res;
    }
}