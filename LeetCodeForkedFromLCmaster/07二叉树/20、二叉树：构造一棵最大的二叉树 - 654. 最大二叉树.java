2nd
执行结果：
通过
显示详情
添加备注

执行用时：
2 ms
, 在所有 Java 提交中击败了
90.97%
的用户
内存消耗：
41.1 MB
, 在所有 Java 提交中击败了
15.31%
的用户
通过测试用例：
107 / 107

没达到最好，我觉得肯定是递归的时候进行了 重复找寻最大值的操作。
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int begin = 0;
        int end = nums.length;
        TreeNode root = helper(nums, 0, end);
        return root;

    }
    //递归函数的功能应该是：
    //找到最大节点并且作为root，然后对root.left/right进行helper递归，最后返回root
    public TreeNode helper(int[] nums, int left, int right){
        if(left == right)
            return null;
        TreeNode root;
        int maxIndex = left;
        for(int i = left; i < right; i++){
            if(nums[i] > nums[maxIndex]){
                maxIndex = i;
            }
        }
        root = new TreeNode(nums[maxIndex]);
        root.left = helper(nums, left, maxIndex);
        root.right = helper(nums, maxIndex + 1, right);
        return root;
    }
}

####
达到100%时间复杂度的是将[,)改为[,]的一版题解。
class Solution {
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		return f(nums, 0, nums.length - 1);
	}

	private TreeNode f(int[] nums, int L, int R) {
		if (L > R) {
			return null;
		}
		if (L == R) {
			return new TreeNode(nums[L]);
		}
		int max = -1;
		int r = 0;
		for (int i = L; i <= R; i++) {
			int num = nums[i];
			if (num > max) {
				max = num;
				r = i;
			}
		}
		TreeNode root = new TreeNode(max);
		root.left = this.f(nums, L, r - 1);
		root.right = this.f(nums, r + 1, R);
		return root;
	}
}
