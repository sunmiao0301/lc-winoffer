第一版
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null)return 0;
        int depth = 1;
        findPath(root, 1, depth);
        return depth;
    }
    void findPath(TreeNode root, int i, int depth){
        if(root.left != null){
            findPath(root.left, i + 1, depth);
        }
        if(root.right != null){
            findPath(root.right, i + 1, depth);
        }
        if(root.left == null && root.right == null && i > depth)
            depth = i;
    }
}

输入
[3,9,20,null,null,15,7]
输出
1
预期结果
3

这涉及到java的特性

第二版
class Solution {
    public int depth = 1;
    public int maxDepth(TreeNode root) {
        if(root == null)return 0;
        findPath(root, 1);
        return depth;
    }
    public void findPath(TreeNode root, int i){
        if(root.left != null){
            findPath(root.left, i + 1);
        }
        if(root.right != null){
            findPath(root.right, i + 1);
        }
        if(root.left == null && root.right == null && i > depth)
            depth = i;
    }
}
