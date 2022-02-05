2nd
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return 1;
        else if(root.left == null && root.right != null)
            return 1 + minDepth(root.right);
        else if(root.left != null && root.right == null)
            return 1 + minDepth(root.left);
        else
            return Math.min(1 + minDepth(root.right), 1 + minDepth(root.left));
    }
}

//第一版如下 出错 问题在于没有理解题意 
最小深度是小深度是从根节点到最近叶子节点的最短路径上的节点数量。
执行结果：
解答错误
通过测试用例：
28 / 52
输入：
[2,null,3,null,4,null,5,null,6]
输出：
1
预期结果：
5
class Solution {
    public int minDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int minDepth = 0;
        if(root == null) return minDepth;
        queue.offer(root);
        minDepth++;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                TreeNode temp = queue.poll();
                if(temp.left == null)
                    return minDepth;
                else
                    queue.offer(temp.left);
                if(temp.right == null)
                    return minDepth;
                else
                    queue.offer(temp.right);
                size--;
            }
            minDepth++;
        }
        return minDepth;
    }
}

//第二版 通过 其实重点就在于要清楚 叶子结点 的概念
一棵树当中没有子结点（即度为0）的结点称为叶子结点，简称“叶子”。 叶子是指出度为0的结点，又称为终端结点。
这一题用层次遍历（广度优先）肯定是最快的
复杂度分析
时间复杂度：O(N)O(N)，其中 NN 是树的节点数。对每个节点访问一次。
空间复杂度：O(H)O(H)，其中 HH 是树的高度。空间复杂度主要取决于递归时栈空间的开销，最坏情况下，树呈现链状，空间复杂度为 O(N)O(N)。平均情况下树的高度与节点数的对数正相关，空间复杂度为 O(\log N)O(logN)。
class Solution {
    public int minDepth(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int minDepth = 0;
        if(root == null) return minDepth;
        queue.offer(root);
        minDepth++;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                TreeNode temp = queue.poll();
                if(temp.left == null && temp.right == null)//一棵树当中没有子结点（即度为0）的结点称为叶子结点，简称“叶子”。 叶子是指出度为0的结点，又称为终端结点。
                    return minDepth;
                if(temp.right != null)
                    queue.offer(temp.right);
                if(temp.left != null)
                    queue.offer(temp.left);
                size--;
            }
            minDepth++;
        }
        return 301;
    }
}

//此外 本题还可以用递归法（深度优先）做 相比广度优先法 这种方法要慢一些
复杂度分析
时间复杂度：O(N)O(N)，其中 NN 是树的节点数。对每个节点访问一次。
空间复杂度：O(N)O(N)，其中 NN 是树的节点数。空间复杂度主要取决于队列的开销，队列中的元素个数不会超过树的节点数。
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        return helper(root, 1);
    }
    int helper(TreeNode root, int num){
        if(root.left == null && root.right == null) 
            return num;
        else if(root.left == null)
            return helper(root.right, num + 1);
        else if(root.right == null)
            return helper(root.left, num + 1);
        else
            return Math.min(helper(root.left ,num + 1), helper(root.right, num + 1));
    }
}
