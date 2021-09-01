/*
首先还是二叉树的定义来一遍 
*/
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

//然后开始非递归法写三种深度优先遍历
//辅助栈 先序遍历 根左右 还是根据力扣里面的要求来吧 - LeetCode 144
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
36.8 MB
, 在所有 Java 提交中击败了
34.35%
的用户
通过测试用例：
69 / 69
class Solution {
    //根左右
    public List<Integer> preorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    List<Integer> ret = new ArrayList<>();
    if(root == null)
        return ret;
    stack.push(root);
    while(!stack.isEmpty()){
        TreeNode temp = stack.pop();
        if(temp.right != null)
            stack.push(temp.right);
        if(temp.left != null)
            stack.push(temp.left);
        ret.add(temp.val);
    }
    return ret;
    }
}

//辅助栈 后序遍历 左右根 还是根据力扣里面的要求来吧 - LeetCode 145


//辅助栈 中序遍历 左根右 还是根据力扣里面的要求来吧 - LeetCode 94
这一题中序遍历非递归法思考的时候感觉用迭代法还是有点难度的，主要在于只能用栈。
