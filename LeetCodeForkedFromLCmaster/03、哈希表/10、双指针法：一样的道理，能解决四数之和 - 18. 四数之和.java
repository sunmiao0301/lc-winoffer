//第一版 瞟了一眼题解之后 一遍过

对于这类《几个数之和》题型 在此稍微总结一下：
1）如果这几个数是来着多个数组，那么HashMap即可解决，通过一分为2可以降低空间复杂度
   比如来自四个数组的《四数之和》
   那么 1 + 1 + 1 + 1 就可以化为 2 + 2
2) 如果这几个数是来自一个数组，且不允许重复（也就是求组合的结果，不是求排列结果）
   那么就用双指针，之前的嵌套层数是 数字数 - 2（双指针）
   比如《三数之和》 就是 3 - 2 = 1，双指针之外的for嵌套就是一层
   比如《四数之和》 就和 4 - 2 = 2，双指针之外的for嵌套就是两层
  
四数之和，和15.三数之和是一个思路，都是使用双指针法, 基本解法就是在15.三数之和 的基础上再套一层for循环。
但是有一些细节需要注意，例如： 不要判断nums[k] > target 就返回了，三数之和 可以通过 nums[i] > 0 就返回了，因为 0 已经是确定的数了，四数之和这道题目 target是任意值。（大家亲自写代码就能感受出来）
15.三数之和的双指针解法是一层for循环num[i]为确定值，然后循环内有left和right下表作为双指针，找到nums[i] + nums[left] + nums[right] == 0。
四数之和的双指针解法是两层for循环nums[k] + nums[i]为确定值，依然是循环内有left和right下表作为双指针，找出nums[k] + nums[i] + nums[left] + nums[right] == target的情况，三数之和的时间复杂度是O(n^2)，四数之和的时间复杂度是O(n^3) 。
那么一样的道理，五数之和、六数之和等等都采用这种解法。
对于15.三数之和双指针法就是将原本暴力O(n^3)的解法，降为O(n^2)的解法，四数之和的双指针解法就是将原本暴力O(n^4)的解法，降为O(n^3)的解法。
之前我们讲过哈希表的经典题目：454.四数相加II，相对于本题简单很多，因为本题是要求在一个集合中找出四个数相加等于target，同时四元组不能重复。
而454.四数相加II是四个独立的数组，只要找到A[i] + B[j] + C[k] + D[l] = 0就可以，不用考虑有重复的四个元素相加等于0的情况，所以相对于本题还是简单了不少！
我们来回顾一下，几道题目使用了双指针法。
双指针法将时间复杂度O(n^2)的解法优化为 O(n)的解法。也就是降一个数量级，题目如下：
27.移除元素
15.三数之和
18.四数之和 
  
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
    /*
    给你一个由 n 个整数组成的数组 nums
    a、b、c 和 d 互不相同
    是否可以认为这n个整数互不相同呢？
    不可以。。。这题的隐藏含义与三数之和一样
    */
    List<List<Integer>> ret = new ArrayList<>();
    Arrays.sort(nums);
    for(int i = 0; i < nums.length - 3; i++){
        if(i > 0 && nums[i] == nums[i - 1])continue;//·······················注意这里
        for(int j = i + 1; j < nums.length - 2; j++){
            if(j != i + 1 && nums[j] == nums[j - 1])continue;//······················注意这里，第一个数要除掉，不然j的第一个值如果和i值一样，就取不到j的第一个值了
            int l = j + 1;
            int r = nums.length - 1;
            while(l < r){
                if(nums[i] + nums[j] + nums[l] + nums[r] > target)
                    r--;
                else if(nums[i] + nums[j] + nums[l] + nums[r] < target)
                    l++;
                else{
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    ret.add(list);
                    r--;
                    while(l < r && nums[r] == nums[r + 1]){
                        r--;
                    }
                }
            }
        }
    }
    return ret;
    }
}
