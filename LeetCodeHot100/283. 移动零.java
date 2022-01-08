第一版
class Solution {
    public void moveZeroes(int[] nums) {
        //必须在原数组上操作，不能拷贝额外的数组。
        //我的理解是这是除零之外的数组排序？
        //看了一眼题解区 居然都说很简单
        //果然 仔细看了看题目
        //是我理解错误 也是因为实例给的很有误导性了
        //同时保持非零元素的相对顺序
        //但是我还是只想到“暴力”       
    }
}

第二版
看了题解 写了一般 但是写的巨丑...
执行结果：
通过
执行用时：
2 ms
, 在所有 Java 提交中击败了
59.89%
的用户
内存消耗：
39.5 MB
, 在所有 Java 提交中击败了
73.67%
的用户
通过测试用例：
74 / 74
class Solution {
    public void moveZeroes(int[] nums) {
        if(nums.length == 1)return;
        int left_find_zero = 0;
        while(nums[left_find_zero] != 0){
            left_find_zero++;
            if(left_find_zero == nums.length)
                return;
        }
        int right_find_none_zero = left_find_zero + 1;
        if(right_find_none_zero == nums.length)
                return;
        while(nums[right_find_none_zero] == 0){
            right_find_none_zero++;
            if(right_find_none_zero == nums.length)
                return;
        }
        //防止全为0

        while(right_find_none_zero < nums.length){
            while(nums[right_find_none_zero] == 0){
                right_find_none_zero++;
                if(right_find_none_zero == nums.length)
                    return;
            }
            swap(nums, left_find_zero, right_find_none_zero);
            left_find_zero++;
        }
    return;
    }
    void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}

第三版
又写了一版
class Solution {
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        int left = 0;
        while(left < len && nums[left] != 0)
            left++;
        int right = left + 1;
        while(right < len && nums[right] == 0)
            right++;

        while(right < len && left < len){
            swap(nums, left, right);
            left++;
            while(right < len && nums[right] == 0)
                right++;
        }
        return;
    }
    void swap(int[] nums, int a, int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}

####
方法一：双指针
思路及解法
使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
注意到以下性质：
1、左指针左边均为非零数；
2、右指针左边直到左指针处均为零。
因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。

class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}

####
一次遍历
这里参考了快速排序的思想，快速排序首先要确定一个待分割的元素做中间点x，然后把所有小于等于x的元素放到x的左边，大于x的元素放到其右边。
这里我们可以用0当做这个中间点，把不等于0(注意题目没说不能有负数)的放到中间点的左边，等于0的放到其右边。
这的中间点就是0本身，所以实现起来比快速排序简单很多，我们使用两个指针i和j，只要nums[i]!=0，我们就交换nums[i]和nums[j]
class Solution {
	public void moveZeroes(int[] nums) {
		if(nums==null) {
			return;
		}
		//两个指针i和j
		int j = 0;
		for(int i=0;i<nums.length;i++) {
			//当前元素!=0，就把其交换到左边，等于0的交换到右边
			if(nums[i]!=0) {
				int tmp = nums[i];
				nums[i] = nums[j];
				nums[j++] = tmp;
			}
		}
	}
}	

