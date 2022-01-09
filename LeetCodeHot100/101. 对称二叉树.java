第一版 递归
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
84.64%
的用户
通过测试用例：
197 / 197
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return helper(root.left, root.right);
    }
    boolean helper(TreeNode left, TreeNode right){
        if(left == null && right == null)
            return true;
        if(left == null || right == null)
            return false;
        if(left.val == right.val){
            return helper(left.left, right.right) && helper(left.right, right.left);
        }
        return false;

    }
}

第二版 迭代
执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
24.55%
的用户
内存消耗：
37.7 MB
, 在所有 Java 提交中击败了
14.64%
的用户
通过测试用例：
197 / 197
class Solution {
    public boolean isSymmetric(TreeNode root) {
        //可以用一个deque实现迭代
        Deque<TreeNode> d = new LinkedList<>();
        d.addFirst(root.left);
        d.addLast(root.right);
        while(!d.isEmpty()){
            if(d.peekFirst() == null && d.peekLast() == null){
                d.pollFirst();
                d.pollLast();
            }
            else if((d.peekFirst() == null || d.peekLast() == null) || d.peekFirst().val != d.peekLast().val)
                return false;
            else{
                TreeNode tempF = d.pollFirst();
                TreeNode tempL = d.pollLast();
                if(tempF != null){
                    d.addFirst(tempF.right);
                    d.addFirst(tempF.left);
                    d.addLast(tempL.left);
                    d.addLast(tempL.right);
                }
            }
        }
        return true;
    }
}
