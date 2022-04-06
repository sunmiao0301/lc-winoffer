2nd 但是我这一版实际上是新建了一个全局变量res，不优雅，不符合java中函数方法的定义。
class Solution {
    public int res = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        helper(root);
        return res;
    }
    public void helper(TreeNode root){
        if(root == null)
            return;
        else if(root.left == null){
            helper(root.right);
        }
        else{ //if(root.left != null){
            if(root.left.left == null && root.left.right == null){
                res += root.left.val;
                helper(root.right);
            }
            else{
                helper(root.left);
                helper(root.right);
            }
        }
    }
}
2nd 
优化一下思路 
如果想要不声明一个全局变量来实现这个方法 p.s.全局变量线程不安全，最好用栈内变量
并且不想用迭代（广度优先），而想要用深度优先的话 p.s.实际上效率和广度优先一样
需要在方法中用到一些不常见的编程思想。

//第一版 一遍过
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
36.1 MB
, 在所有 Java 提交中击败了
75.32%
的用户
通过测试用例：
100 / 100
class Solution {
    int leftSum = 0;
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null) return 0;
        sumhelper(root);
        return leftSum;
    }
    void sumhelper(TreeNode root){
        if(root.left != null){
            if(root.left.left == null && root.left.right == null)
                leftSum += root.left.val;
            else
                sumhelper(root.left);
        }
        if(root.right != null){
            sumhelper(root.right);
        }
    }
}

//另 也可以用广度优先

但是
复杂度分析
时间复杂度：O(n)O(n)，其中 nn 是树中的节点个数。
空间复杂度：O(n)O(n)。空间复杂度与广度优先搜索使用的队列需要的容量相关，为 O(n)O(n)。

