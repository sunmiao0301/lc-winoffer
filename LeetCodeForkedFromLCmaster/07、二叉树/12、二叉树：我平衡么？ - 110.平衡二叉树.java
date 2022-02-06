2nd
第二次第一版我是自顶向下写的 也知道这样写肯定不是最好的办法
class Solution {
    public boolean isBalanced(TreeNode root) {
        //分析一下可知 平衡二叉树需要每个节点的左右子树高度差都满足 （root满足不代表其子节点满足）
        //直接用helper()DFS得到每个子树高度的方法 有很多次重复遍历 肯定不是最快
        if(root == null)
            return true;
        //if(root.left != null && root.right != null)
            return isBalanced(root.left) && isBalanced(root.right) && (Math.abs(helper(root.left) - helper(root.right)) > 1 ? false : true);
    }
    public int helper(TreeNode root){
        if(root == null)
            return 0;
        /**
        else if(root.left == null && root.right == null)
            return 1;
        else if(root.left != null && root.right == null)
            return 1 + helper(root.left);
        else if(root.left == null && root.right != null)
            return 1 + helper(root.right);
        */
        //上面这几行可以省略
        else
            return Math.max(1 + helper(root.left), 1 + helper(root.right));
    }
}



/*
第一版 这一题的难度其实在于如何判断和理解 最小高度和最大高度
但是这一题的题干中 其实是给定一个二叉树，判断它是否是高度平衡的二叉树。
本题中，一棵高度平衡二叉树定义为：
一个二叉树每个节点 的左右两个 子树 的高度差的绝对值不超过 1 。
也就是 子树的高度!
要理解这句 “子树的高度” 看下面两个例子就明白了
[1,2,3,4,5,6,null,8] ---> true
[1,null,2,null,3]    ---> false
[1,2,2,3,null,null,3,4,null,null,4] ---> false
翻译一下，就是对于任意一个节点 其子树都得是平衡的（左右的最大深度差不超过1）
所以思路就出来了：
*/
有幸和k神的方法二一致 但是不是最好的方法
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return Math.abs(maxDepth(root.left, 0) - maxDepth(root.right, 0)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }
    int maxDepth(TreeNode root, int num){//root, 0
        if(root == null) 
            return num;
        else
            return Math.max(maxDepth(root.left, num + 1), maxDepth(root.right, num + 1));
    }
}

//K神的第二种方法
从底至顶（提前阻断）
此方法为本题的最优解法，但“从底至顶”的思路不易第一时间想到。

思路是对二叉树做先序遍历，从底至顶返回子树最大高度，若判定某子树不是平衡树则 “剪枝” ，直接向上返回。

算法流程：
recur(root):

递归返回值：
当节点root 左 / 右子树的高度差 < 2<2 ：则返回以节点root为根节点的子树的最大高度，即节点 root 的左右子树中最大高度加 11 （ max(left, right) + 1 ）；
当节点root 左 / 右子树的高度差 \geq 2≥2 ：则返回 -1−1 ，代表 此子树不是平衡树 。
递归终止条件：
当越过叶子节点时，返回高度 00 ；
当左（右）子树高度 left== -1 时，代表此子树的 左（右）子树 不是平衡树，因此直接返回 -1−1 ；
isBalanced(root) ：

返回值： 若 recur(root) != 1 ，则说明此树平衡，返回 truetrue ； 否则返回 falsefalse 。
复杂度分析：
时间复杂度 O(N)O(N)： NN 为树的节点数；最差情况下，需要递归遍历树的所有节点。
空间复杂度 O(N)O(N)： 最差情况下（树退化为链表时），系统递归需要使用 O(N)O(N) 的栈空间。
Java

class Solution {
    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null) return 0;
        int left = recur(root.left);
        if(left == -1) return -1;
        int right = recur(root.right);
        if(right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }
}
