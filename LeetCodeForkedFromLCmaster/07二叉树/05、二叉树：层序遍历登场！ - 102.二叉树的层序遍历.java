2nd
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        LinkedList<Integer> valueOfQueue = new LinkedList<>();

        if(root == null) return res;
        
        queue.add(root);
        valueOfQueue.add(root.val);

        res.add((LinkedList<Integer>)valueOfQueue.clone());

        int flag = 1;
        while(!queue.isEmpty()){
            TreeNode p = queue.removeFirst();
            valueOfQueue.removeFirst();
            flag--;
            if(p.left != null){
                queue.add(p.left);
                valueOfQueue.add(p.left.val);
            }
            if(p.right != null){
                queue.add(p.right);
                valueOfQueue.add(p.right.val);
            }
            if(flag == 0 && !queue.isEmpty()){
                res.add( (LinkedList<Integer>) valueOfQueue.clone());
                flag = queue.size();
            }
        }
        return res;
    }
}

//第一版 easy
执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
92.37%
的用户
内存消耗：
38.4 MB
, 在所有 Java 提交中击败了
86.39%
的用户
通过测试用例：
34 / 34
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null) return ret;
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            while(size > 0){
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if(temp.left != null)
                    queue.offer(temp.left);
                if(temp.right != null)
                    queue.offer(temp.right);
                size--;
            }
            ret.add(list);
        }
        return ret;
    }
}

学会二叉树的层序遍历，可以一口气撸完leetcode上八道题目：

102.二叉树的层序遍历
107.二叉树的层次遍历II
199.二叉树的右视图
637.二叉树的层平均值
429.N叉树的前序遍历
515.在每个树行中找最大值
116.填充每个节点的下一个右侧节点指针
117.填充每个节点的下一个右侧节点指针II

//标准题解之 递归层序遍历
class Solution {
    List<List<Integer>> list = new ArrayList<>();
    public List<List<Integer>> levelOrder(TreeNode root) {
        dns(root,0);
        return list;
    }
    public void dns(TreeNode node,int lever){
        if(node == null) return;
        if(list.size()==lever) list.add(new ArrayList<Integer>());

        list.get(lever).add(node.val);

        dns(node.left,lever+1);
        dns(node.right,lever+1);
    }
}
