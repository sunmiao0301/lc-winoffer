## 第一版 虽然通过了 但是效率很低

执行结果：
通过
显示详情
添加备注

执行用时：
3 ms
, 在所有 Java 提交中击败了
6.48%
的用户
内存消耗：
44.3 MB
, 在所有 Java 提交中击败了
5.01%
的用户
通过测试用例：
115 / 115

class Solution {
    public List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        //树中节点总数在范围 [0, 5000] 内
        //-1000 <= targetSum <= 1000 -- 由于可能是负数 所以还是得依靠叶子节点的限制
        ArrayList<Integer> tmp = new ArrayList<>();
        if(root == null) return res;
        helper(root, targetSum, tmp);
        return res;
    }
    public void helper(TreeNode root, int sum, ArrayList<Integer> tmp){
        if(root.left == null && root.right == null && sum - root.val == 0){
            tmp.add(root.val);
            res.add(tmp);
        }
        if(root.left != null){
            ArrayList<Integer> tmpClone = (ArrayList<Integer>)tmp.clone(); //(ArrayList<String>)sites.clone();
            tmpClone.add(root.val);
            helper(root.left, sum - root.val, tmpClone);
        }
        if(root.right != null){
            ArrayList<Integer> tmpClone = (ArrayList<Integer>)tmp.clone();
            tmpClone.add(root.val);
            helper(root.right, sum - root.val, tmpClone);            
        }
    }
}

## 题解之所以快 是因为用了递归回溯

class Solution {
    List<List<Integer>> ret = new LinkedList<List<Integer>>();
    Deque<Integer> path = new LinkedList<Integer>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(root, targetSum);
        return ret;
    }

    public void dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        path.offerLast(root.val);
        targetSum -= root.val;
        if (root.left == null && root.right == null && targetSum == 0) {
            ret.add(new LinkedList<Integer>(path));
        }
        dfs(root.left, targetSum);
        dfs(root.right, targetSum);
        path.pollLast();
    }
}
