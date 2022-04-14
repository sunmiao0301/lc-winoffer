## 第一版 知道是摩尔投票 但是详细算法却想不起来

## 题解

我们举一个具体的例子，例如下面的这个数组：
[7, 7, 5, 7, 5, 1 | 5, 7 | 5, 5, 7, 7 | 7, 7, 7, 7]
在遍历到数组中的第一个元素以及每个在 | 之后的元素时，candidate 都会因为 count 的值变为 0 而发生改变。最后一次 candidate 的值从 5 变为 7，也就是这个数组中的众数。

执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
99.91%
的用户
内存消耗：
44.8 MB
, 在所有 Java 提交中击败了
53.99%
的用户
通过测试用例：
43 / 43

class Solution {
    public int majorityElement(int[] nums) {
        //摩尔投票
        //尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
        int candidate = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] != candidate){
                count--;
            }
            else{
                count++;
            }
            if(count == 0){
                candidate = nums[++i];
                count++;
            }
        }
        return candidate;
    }
}
