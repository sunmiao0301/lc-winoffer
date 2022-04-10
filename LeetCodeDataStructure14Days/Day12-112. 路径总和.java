## 第一版 不借助 helper() 写出来了 主要是妙用 terget - root.val 作为参数进行传递

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
73.78%
的用户
通过测试用例：
117 / 117

class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        //根节点到叶子节点 的路径
        //树中节点的数目在范围 [0, 5000] 内
        if(root == null){
            return false;
        }
        else if(root.left == null && root.right == null && root.val == targetSum){
            return true;
        }
        else if(root.left != null || root.right != null){
            return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
        }
        // else if(root.left != null){
        //     return hasPathSum(root.left, targetSum - root.val);
        // }
        // else if(root.right != null){
        //     return hasPathSum(root.right, targetSum - root.val);
        // }
        else{
            return false;
        }
    }
}

## 题解
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
