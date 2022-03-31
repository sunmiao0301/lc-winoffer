## 第一版 先想了一个不需要额外空间的思路 但是有点难
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        /** --- 设计时间复杂度为 m + n 也就是只遍历一遍
        i -- nums1当前索引的值
        j -- nums1中被nums2挤走的值
        k -- nums2中当前索引的值
        */
        int i, j, k = 0;
        while(i != nums1.length){
            if(nums2[j] > )
        }
    }
}


## 第二版 先实现再优化 过早的优化是噩梦的开端


## 最佳题解（逆向双指针 ---可以证明可知不会出现重复 -- 因为最极端的情况也是 nums2 全部先放，也只会存满nums1的后n个空位置。
