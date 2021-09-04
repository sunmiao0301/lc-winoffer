//第一版 通过 但是效率一般 用的是最基本的层序遍历（相当于模拟）
//写完之后发现了下面这句话：
//遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
执行结果：
通过
执行用时：
4 ms
, 在所有 Java 提交中击败了
15.10%
的用户
内存消耗：
41.3 MB
, 在所有 Java 提交中击败了
5.19%
的用户
通过测试用例：
18 / 18
class Solution {
    public int countNodes(TreeNode root) {
    /*
    完全二叉树通过最右边的值就可以判断这一层是否填满
    那么用队列来实现的话，就是右边最先进队列 方便判断 然后总数通过一个等比数列层层记载
    */
        Queue<TreeNode> queue = new LinkedList<>();
        int nodeNum = 0;
        int nodeTwo = 1;
        if(root == null) return nodeNum;
        queue.offer(root);
        nodeNum++;
        while(queue.peek().right != null){
            int size = queue.size();
            while(size > 0){
                TreeNode temp = queue.poll();
                queue.offer(temp.right);
                queue.offer(temp.left);
                size--;
            }
            nodeTwo *= 2;
            nodeNum += nodeTwo;
        }
        nodeTwo *= 2;
        nodeNum += nodeTwo;
        while(!queue.isEmpty() && nodeTwo != 0){
            int size = queue.size();
            while(size > 0){
                TreeNode temp = queue.poll();
                if(temp.right == null) 
                    nodeNum--;
                else{
                    nodeTwo = 0;
                    break;
                }
                if(temp.left == null)
                    nodeNum--;
                else{
                    nodeTwo = 0;
                    break;
                }
                size--;
            }
        }
        return nodeNum;
    }
}

//第二版 标准题解1） 递归法 很快了 但是没有把条件中的“完全二叉树”用上 但这种方法适用于任何二叉树
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
40.8 MB
, 在所有 Java 提交中击败了
69.98%
的用户
通过测试用例：
18 / 18
class Solution {
     public int countNodes(TreeNode root) {
        //base case
        if (root == null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}

PS：到此，可以发现其实前一题 二叉树的深度的递归法也可能写的更简单 如下
private int countLevel(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(countLevel(root.left),countLevel(root.right)) + 1;
}

//第三版 标准题解2） 也是最佳解法
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null){
           return 0;
        } 
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if(left == right){
            return countNodes(root.right) + (1<<left);
        }else{
            return countNodes(root.left) + (1<<right);
        }
    }
    private int countLevel(TreeNode root){
        int level = 0;
        while(root != null){
            level++;
            root = root.left;
        }
        return level;
    }
}
