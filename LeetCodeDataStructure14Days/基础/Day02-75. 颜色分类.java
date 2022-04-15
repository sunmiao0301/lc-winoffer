## 第一版 没想出来 -- 想到了是双指针 但是没想到具体的实现方案
class Solution {
    public void sortColors(int[] nums) {
        //只包含1、2、3的原地排序
        //你能想出一个仅使用常数空间的一趟扫描算法吗？
        //三指针？一个从前往后 -- 指向第一个非0数 一个从后往前指向第一个非2的数 一个在中间，指向非1\0的数

        //双指针？前指针指向1、2；后指针指向0、1
        //如果有一个是1
    }
}

## 题解 本题是经典的「荷兰国旗问题」，由计算机科学家 Edsger W. Dijkstra 首先提出

## 第二版 首先先实现了 但是用的是单指针 并且遍历了不止单趟

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
40 MB
, 在所有 Java 提交中击败了
13.94%
的用户
通过测试用例：
87 / 87

class Solution {
    public void sortColors(int[] nums) {
        //单指针
        int noZero = 0;
        int i = nums.length - 1;
        while(noZero < i){
            while(noZero < i && nums[noZero] == 0){
                noZero++;
            }
            while(noZero < i && nums[i] != 0){
                i--;
            }
            if(noZero >= i){
                break;
            }
            swap(noZero, i, nums);
        }
        int noOne = noZero;
        i = nums.length - 1;

        while(noOne < i){
            while(noOne < i && nums[noOne] == 1){
                noOne++;
            }
            while(noOne < i && nums[i] != 1){
                i--;
            }
            if(noOne >= i){
                break;
            }
            swap(noOne, i, nums);
        }

    }
    public void swap(int a, int b, int[] arr){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}

p.s. 单指针的题解

class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
    }
}

## 第二版 用双指针 单趟解决
