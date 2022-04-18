## 第一版 递归

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
39.8 MB
, 在所有 Java 提交中击败了
8.02%
的用户
通过测试用例：
68 / 68

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        //迭代 左右根
        List<Integer> res = new ArrayList<>();
        if(root == null)
            return res;
        helper(root, res);
        return res;
    }
    public void helper(TreeNode root, List<Integer> res){
        if(root.left != null)
            helper(root.left, res);
        if(root.right != null)
            helper(root.right, res);
        res.add(root.val);
    }
}

## 题解 迭代
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }
}
