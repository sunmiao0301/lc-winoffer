看了题解思路之后的第一版
执行结果：
通过
执行用时：
4 ms
, 在所有 Java 提交中击败了
58.02%
的用户
内存消耗：
47.2 MB
, 在所有 Java 提交中击败了
69.24%
的用户
通过测试用例：
33 / 33
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //动态数组？然后转化为数组用于返回
        //时间复杂度o(n) 不使用额外空间
        
        //含 n 个整数的数组 nums 是解题的关键
        //思路是 用数组代替哈希表
        int n = nums.length;
        int temp;
        for(int i = 0; i < n; i++){
            //1出现 就把nums[0] + n
            temp = nums[i] % n;
            if(temp == 0)
                nums[n - 1] += n;
            else
                nums[nums[i] % n - 1] += n;
        }
        List<Integer> array = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(nums[i] <= n)
                array.add(i + 1);
        }
        return array;
    }
}

####
题解 
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}

看了题解代码之后的第二版
执行结果：
通过

执行用时：
3 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
47.4 MB
, 在所有 Java 提交中击败了
45.97%
的用户
通过测试用例：
33 / 33
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //含 n 个整数的数组 nums 是解题的关键
        //思路是 用数组代替哈希表
        int n = nums.length;
        for(int i = 0; i < n; i++){
            //1出现 就把nums[0] + n
            nums[(nums[i] - 1) % n] += n;
            /* 
            比第一版的优化在于把 
            temp = nums[i] % n;
            if(temp == 0)
                nums[n - 1] += n;
            else
                nums[nums[i] % n - 1] += n;
            替换为
            nums[(nums[i] - 1) % n] += n;
            */
        }
        List<Integer> array = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(nums[i] <= n)
                array.add(i + 1);
        }
        return array;
    }
}
