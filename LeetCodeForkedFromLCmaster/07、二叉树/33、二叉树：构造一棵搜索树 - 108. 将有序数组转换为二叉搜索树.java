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
