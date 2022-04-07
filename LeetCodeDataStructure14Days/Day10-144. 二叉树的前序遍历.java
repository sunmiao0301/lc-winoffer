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
39.3 MB
, 在所有 Java 提交中击败了
73.30%
的用户
通过测试用例：
69 / 69
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        helper(root, res);
        return res;
    }
    public void helper(TreeNode root, List<Integer> res){
        //根左右
        res.add(root.val);
        if(root.left != null){
            helper(root.left, res);
        }
        if(root.right != null){
            helper(root.right, res);
        }
    }
}

## 第一版 迭代
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        //递归 = 栈
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        if(root == null)return res;
        stack.push(root);
        //res.add(root.val);
        while(!stack.isEmpty()){
            TreeNode tmp = stack.pop();
            res.add(tmp.val);
            if(tmp.right != null){
                stack.push(tmp.right);
            }
            if(tmp.left != null){
                stack.push(tmp.left);
            }
        }
        return res;
    }
}
