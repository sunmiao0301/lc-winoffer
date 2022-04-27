## 第一版 想写点简单点 结果写错了 没考虑到情况

执行结果：
执行出错
显示详情
添加备注

java.lang.ArrayIndexOutOfBoundsException: Index 2 out of bounds for length 2
  at line 9, Solution.sortArrayByParity
  at line 54, __DriverSolution__.__helper__
  at line 84, __Driver__.main
最后执行的输入：
[0,2]

class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l < r){
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
            while(nums[l] % 2 == 0){
                l++;
            }
            while(nums[r] % 2 == 1){
                r--;
            }
        }
        return nums;
    }
}

## 第二版 通过

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
42.2 MB
, 在所有 Java 提交中击败了
43.48%
的用户
通过测试用例：
285 / 285

class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l < r){
            while(nums[l] % 2 == 0 && l < r){
                l++;
            }
            while(nums[r] % 2 == 1 && r > l){
                r--;
            }
            int tmp = nums[l];
            nums[l] = nums[r];
            nums[r] = tmp;
        }
        return nums;
    }
}
