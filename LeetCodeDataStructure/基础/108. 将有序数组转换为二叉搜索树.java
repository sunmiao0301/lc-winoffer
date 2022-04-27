## 第一版 之前这题居然一直没通过 因为没有理解题目中的“每个节点的左右两个子树的高度差的绝对值不超过 1 ” 所以一直把想简单了 
## 以为可以直接构造一个下面这样的树
        /\
       /  \
      /    \
     /      \
             \

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
41 MB
, 在所有 Java 提交中击败了
85.54%
的用户
通过测试用例：
31 / 31

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        //每个节点的左右两个子树的高度差的绝对值不超过 1 
        //已经是排序数组，那么直接无限二分算了
        // 1 1 1 1 -- length / 2 = 2 || (0 + (3 - 0) / 2) = 1
        // 1 1 1 1 1 -- length / 2 = 2 || = (0 + (4 - 0) / 2) = 2 
        // int len = nums.length;
        int mid = 0 + (nums.length - 0) / 2;
        TreeNode ret = new TreeNode(nums[mid]);
        // int left_left = 0;
        // int left_right = len / 2 - 1;
        // int right_left = len / 2 + 1;
        // int right_right = len - 1;
        ret.left = helper(nums, 0, mid - 1);
        ret.right = helper(nums, mid + 1, nums.length - 1);
        
        return ret;

    }
    public TreeNode helper(int[] nums, int left, int right){
        if(left > right){
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode ret = new TreeNode(nums[mid]);
        ret.left = helper(nums, left, mid - 1);
        ret.right = helper(nums, mid + 1, right);

        return ret;
    }
}
