## 二刷 - 下面是我二刷写的第一版 但是有错：如A处，root.left的值拿到是在helper函数的最后，而在这之前，helper函数就需要一个root.left值，这时候root.left还是空，如此就会报错。
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, root.left, left, mid - 1); ----------------A
        root.right = helper(nums, root.right, left, mid + 1);
        return root;
    }
    public TreeNode helper(int[] nums, TreeNode root, int left, int right){
        int mid = left + right;
        root.left = helper(nums, root.left, left, mid - 1);
        root.right = helper(nums, root.right, left, mid + 1);
        return new TreeNode(nums[mid]);
    }
}

## 二刷 正解（但是我没写出来）
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置左边的数字作为根节点
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }
}

第一版 错误的
原因是我记错了浮点型强制转换整型是向下取整而不是向上取整的！
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        //高度平衡二叉树 不代表数组最中间的值就一定是根节点（因为可能一边是满，一边是一个分支走到头）
        //可以同时维护两个树，并且控制高度差，最后合并即可。
        //1 <= nums.length <= 104

        //但是如果直接拓展一个分支走到头也行啊。。。最简单的做法了。
        int len = nums.length;
        int left = len / 2 - 2;
        int right = len / 2;
        TreeNode root = new TreeNode(nums[len / 2 - 1]);
        TreeNode l = root;
        TreeNode r = root;
        while(left != -1 && right != len){
            l.left = new TreeNode(nums[left]);
            r.right = new TreeNode(nums[right]);
            l = l.left;
            r = r.right;
            left--;
            right++;
        }
        if(left == 0){
            l.left = new TreeNode(nums[left]);
        }
        if(right == len - 1){
            r.right = new TreeNode(nums[right]);
        }
        return root;
    }
}

第二版 略作修改即可 但是不知为何，这居然算错？没有违反题目要求呀。
执行结果：
解答错误
通过测试用例：
5 / 31
输入：
[0,1,2,3,4,5]
输出：
[3,2,4,1,null,null,5,0]
预期结果：
[3,1,5,0,2,4]
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        //高度平衡二叉树 不代表数组最中间的值就一定是根节点（因为可能一边是满，一边是一个分支走到头）
        //可以同时维护两个树，并且控制高度差，最后合并即可。
        //1 <= nums.length <= 104

        //但是如果直接拓展一个分支走到头也行啊。。。最简单的做法了。
        int len = nums.length;
        int left = len / 2 - 1;
        int right = len / 2 + 1;
        TreeNode root = new TreeNode(nums[len / 2]);
        TreeNode l = root;
        TreeNode r = root;
        while(left != -1 && right != len){
            l.left = new TreeNode(nums[left]);
            r.right = new TreeNode(nums[right]);
            l = l.left;
            r = r.right;
            left--;
            right++;
        }
        if(left == 0){
            l.left = new TreeNode(nums[left]);
        }
        if(right == len - 1){
            r.right = new TreeNode(nums[right]);
        }
        return root;
    }
}
