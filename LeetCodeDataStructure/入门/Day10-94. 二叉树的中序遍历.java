## 第一版 递归 -- 迭代的不太熟练

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
39.4 MB
, 在所有 Java 提交中击败了
58.22%
的用户
通过测试用例：
70 / 70

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        //迭代 左根右
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        helper(root, res);
        return res;
    }
    public void helper(TreeNode root, List<Integer> res){
        if(root.left != null){
            helper(root.left, res);
        }
        res.add(root.val);
        if(root.right != null){
            helper(root.right, res);
        }
    }
}

## 题解 迭代 中序遍历
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}
