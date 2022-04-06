第一版
实现如下 但是没有想到怎么返回 所以第二版里面新加了一个函数来使得主函数可以返回
class Solution {
    public int preNode = 0;
    public TreeNode convertBST(TreeNode root) {
        //其实有个简单，但是效率不佳的办法就是用遍历，存到数组里。
        //那么能不能融合一下，中序遍历，然后加。
        //但是中序遍历是递增，不合适，换成反向的中序遍历就行了。
        if(root.right != null)
            return convertBST(root.right);
        root.val = root.val + preNode;
        preNode = root.val;
        if(root.left != null)
            return convertBST(root.left);
    }
}

第二版
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
38.1 MB
, 在所有 Java 提交中击败了
98.66%
的用户
通过测试用例：
215 / 215
class Solution {
    public int preNode = 0;
    public TreeNode convertBST(TreeNode root) {
        //其实有个简单，但是效率不佳的办法就是用遍历，存到数组里。
        //那么能不能融合一下，中序遍历，然后加。
        //但是中序遍历是递增，不合适，换成反向的中序遍历就行了。
        if(root == null) return root;
        helper(root);
        return root;
    }
    public void helper(TreeNode root){
        if(root.right != null)
            convertBST(root.right);
        root.val = root.val + preNode;
        preNode = root.val;
        if(root.left != null)
            convertBST(root.left);
    }
}

#### 根据题解 我的思路是
方法一：反序中序遍历
本题中要求我们将每个节点的值修改为原来的节点值加上所有大于它的节点值之和。
这样我们只需要反序中序遍历该二叉搜索树，记录过程中的节点值之和，并不断更新当前遍历到的节点的节点值，即可得到题目要求的累加树。

但是还有一种时复杂度更好的：
方法二：Morris 遍历
