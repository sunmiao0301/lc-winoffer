## 递归法写二叉树的题 哪怕是mid 也是很优雅 代码量也很少 一遍过
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
        //插入一定是可以插入到叶子节点处的? 不是 而是一定是插入到一个原来没有节点的位置是可以的
        if(root == null){
            return new TreeNode(val);
        }

        if(root.val > val)
            root.left = insertIntoBST(root.left, val);
        else if(root.val < val)
            root.right = insertIntoBST(root.right, val);

        return root;
    }
}
