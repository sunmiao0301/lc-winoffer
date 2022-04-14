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


## 第二版 先实现再优化 过早的优化是噩梦的开端 故实现如下
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
41.2 MB
, 在所有 Java 提交中击败了
47.73%
的用户
通过测试用例：
59 / 59
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] res = new int[m + n];
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < m && j < n){
            if(nums1[i] <= nums2[j]){
                res[k] = nums1[i];
                i++;
                k++;
            }
            else{
                res[k] = nums2[j];
                j++;
                k++;
            }
        }
        while(i < m){
            res[k] = nums1[i];
            i++;
            k++;
        }
        while(j < n){
            res[k] = nums2[j];
            j++;
            k++;
        }
        for(i = 0; i < m + n; i++){
            nums1[i] = res[i];
        }
    }
}


## 最佳题解（逆向双指针 ---可以证明可知不会出现重复 -- 因为最极端的情况也是 nums2 全部先放，也只会存满nums1的后n个空位置。

## 4.2 完成最佳题解
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m + n - 1;
        int i = m - 1;//nums1
        int j = n - 1;//nums2
        while(k > -1 && i > -1 && j > -1){
            if(nums1[i] >= nums2[j]){
                nums1[k] = nums1[i];
                k--;
                i--;
            }
            else{
                nums1[k] = nums2[j];
                k--;
                j--;
            }
        }
        while(i > -1){
            nums1[k] = nums1[i];
            k--;
            i--;
        }
        while(j > -1){
            nums1[k] = nums2[j];
            k--;
            j--;
        }
    }
}
