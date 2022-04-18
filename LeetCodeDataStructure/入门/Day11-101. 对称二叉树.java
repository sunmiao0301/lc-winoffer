## 第一版 本题很明显需要借助一个辅助函数 helper()，因为给的函数入参只有一个节点，没法判断对称。
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
    public boolean isSymmetric(TreeNode root) {
        //树中节点数目在范围 [1, 1000] 内
        return helper(root.left, root.right);
    }
    public boolean helper(TreeNode l, TreeNode r){
        if(l == null && r == null){
            return true;
        }
        else if(l == null || r == null){
            return false;
        }
        else{
            return helper(l.left, r.right) && helper(l.right, r.left) && (l.val == r.val); 
        }
    }
}
