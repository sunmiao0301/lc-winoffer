## 第一版 这题目描述好像有问题 按照我对题目的理解 输入如果是 [2,7,2] 1 的话 应该是 0 作为输出
---> 见第二版

已完成
执行用时：0 ms
输入
[2,7,2]
1
输出
0
预期结果
3

class Solution {
    public int smallestRangeI(int[] nums, int k) {
        //x 是一个范围为 [-k, k] 的整数。
        //最低 分数 
        //本质上就是找数组中相差最小的两个数 然后只要在2k之内就可以抹平 否则就-2k
        //先暴力一版(后面再用排序)
        //1 <= nums.length <= 104
        if(nums.length == 1) return 0;
        int len = nums.length;
        int min = Integer.MAX_VALUE;//最小值一定大于0（取绝对值）
        for(int i = 0; i < len; i++){
            for(int j = i + 1; j < len; j++){
                min = Math.min(min, Math.abs(nums[i] - nums[j]));
            }
        }
        if(min > 2 * k){
            return min - 2 * k;
        }
        return 0;
    }
}

## 第二版 忽略了重要信息 ---> nums 的 分数 是 nums 中最大和最小元素的差值。于是改为如下，通过，但是速度不够快

执行结果：
通过
显示详情
添加备注

执行用时：
3 ms
, 在所有 Java 提交中击败了
73.51%
的用户
内存消耗：
41.9 MB
, 在所有 Java 提交中击败了
33.21%
的用户
通过测试用例：
68 / 68

class Solution {
    public int smallestRangeI(int[] nums, int k) {
        int min = nums[0];
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        if(max - min > 2 * k){
            return max - min - 2 * k;
        }
        return 0;
    }
}

## 第三版 思考了一下，略微修改了一下，100%
  
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
41.7 MB
, 在所有 Java 提交中击败了
59.33%
的用户
通过测试用例：
68 / 68  

  class Solution {
    public int smallestRangeI(int[] nums, int k) {
        int min = nums[0];
        int max = nums[0];
        for(int i = 1; i < nums.length; i++){
            // max = Math.max(max, nums[i]);
            // min = Math.min(min, nums[i]);
            if(nums[i] > max){
                max = nums[i];
            }
            else if(nums[i] < min){
                min = nums[i];
            }
        }
        if(max - min > 2 * k){
            return max - min - 2 * k;
        }
        return 0;
    }
}
