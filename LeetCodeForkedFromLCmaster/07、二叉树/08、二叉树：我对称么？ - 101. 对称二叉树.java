2nd
class Solution {
    public boolean isSymmetric(TreeNode root) {
        //树中节点数目在范围 [1, 1000] 内
        return helper(root.left, root.right);
    }
    public boolean helper(TreeNode left, TreeNode right){
        if(left == null && right == null)
            return true;
        else if(left == null || right == null)
            return false;
        else if(left.val != right.val)
            return false;
        else
            return helper(left.left, right.right) && helper(left.right, right.left);
    }
}

//第一版 一遍过 递归法
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
36.3 MB
, 在所有 Java 提交中击败了
86.43%
的用户
通过测试用例：
197 / 197
class Solution {
    /*
    进阶：
    你可以运用递归和迭代两种方法解决这个问题吗？
    */
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return helper(root.left, root.right);
    }
    boolean helper(TreeNode left, TreeNode right){
        if(left == null && right == null)
            return true;
        else if(left != null && right != null && left.val == right.val)
            return helper(left.left, right.right) && helper(left.right, right.left);
        else
            return false;
    }
}

//第二版 非递归法 自己写的 一遍过
这里其实有个比较巧妙的思路 那就是用deque的话 不需要层序遍历了 直接是由外而内的 具体看代码
class Solution {
    /*
    进阶：
    你可以运用递归和迭代两种方法解决这个问题吗？
    */
    public boolean isSymmetric(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        if(root == null) return true;
        deque.offerFirst(root.left);
        deque.offerLast(root.right);
        while(!deque.isEmpty()){
            if(deque.peekFirst() == null && deque.peekLast() == null){
                deque.pollFirst();
                deque.pollLast();
            }
            else if(deque.peekFirst() != null && deque.peekLast() != null && deque.peekFirst().val == deque.peekLast().val){
                TreeNode tempLeft = deque.pollFirst();
                TreeNode tempRight = deque.pollLast();
                deque.offerFirst(tempLeft.right);
                deque.offerFirst(tempLeft.left);
                deque.offerLast(tempRight.left);
                deque.offerLast(tempRight.right);
            }
            else
                return false;
        }
        return true;
    }
}
