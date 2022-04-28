## 第一版 层序遍历完成 但是速度没100

执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
80.80%
的用户
内存消耗：
40.2 MB
, 在所有 Java 提交中击败了
27.88%
的用户
通过测试用例：
215 / 215
  
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        //层序遍历
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null) return res;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                if(size == 1){
                    res.add(queue.peek().val);
                }
                TreeNode tmp = queue.poll();
                if(tmp.left != null){
                    queue.offer(tmp.left);
                }
                if(tmp.right != null){
                    queue.offer(tmp.right);
                }
                size--;
            }
        }
        return res;
    }
}
