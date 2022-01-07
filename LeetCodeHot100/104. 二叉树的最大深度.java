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


####
方法一：深度优先搜索
####
本方法比我的第一版简单之处在于，其选择了用Math.max()替代了int depth，继而不需要一个全局变量depth
也就能在一个函数里面就能完成递归而不需要额外的函数
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}

####
方法二：广度优先搜索
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans++;
        }
        return ans;
    }
}
