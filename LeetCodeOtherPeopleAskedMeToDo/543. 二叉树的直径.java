## 第一版 我写出来的版本效率却很差 
## 原因是我这版本相当于： 自顶向下 做了很多次重复遍历 --> 题解是自底向上，只做了一次遍历

执行结果：
通过
显示详情
添加备注

执行用时：
9 ms
, 在所有 Java 提交中击败了
10.62%
的用户
内存消耗：
40.8 MB
, 在所有 Java 提交中击败了
76.40%
的用户
通过测试用例：
104 / 104

class Solution {
    public int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        //每个节点的左边的最深 + 右边的最深
        //这条路径可能穿过也可能不穿过根结点。
        traverse(root);
        return max;
    }
    public void traverse(TreeNode root){
        max = Math.max(max, getMaxDepth(root.left) + getMaxDepth(root.right));
        if(root.left != null){
            traverse(root.left);
        }
        if(root.right != null){
            traverse(root.right);
        }
    }
    // public int getNodeDiameter(TreeNode root){
    //     //得到左右的最深长度 L R 然后得到当节点下的最大直径
    //     return getMaxDepth(root.left) + getMaxDepth(root.right);
    // }
    public int getMaxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        return Math.max(1 + getMaxDepth(root.left), 1 + getMaxDepth(root.right));
    }
}

## 题解

执行结果：
通过
显示详情
添加备注

执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
40.8 MB
, 在所有 Java 提交中击败了
83.30%
的用户
通过测试用例：
104 / 104

class Solution {
    int ans;
    public int diameterOfBinaryTree(TreeNode root) {
        ans = 1;
        depth(root);
        return ans - 1;
    }
    public int depth(TreeNode node) {
        if (node == null) {
            return 0; // 访问到空节点了，返回0
        }
        int L = depth(node.left); // 左儿子为根的子树的深度
        int R = depth(node.right); // 右儿子为根的子树的深度
        ans = Math.max(ans, L+R+1); // 计算d_node即L+R+1 并更新ans
        return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
    }
}
复杂度分析

时间复杂度：O(N)O(N)，其中 NN 为二叉树的节点数，即遍历一棵二叉树的时间复杂度，每个结点只被访问一次。

空间复杂度：O(Height)O(Height)，其中 HeightHeight 为二叉树的高度。由于递归函数在递归过程中需要为每一层递归函数分配栈空间，所以这里需要额外的空间且该空间取决于递归的深度，而递归的深度显然为二叉树的高度，并且每次递归调用的函数里又只用了常数个变量，所以所需空间复杂度为 O(Height)O(Height) 。
