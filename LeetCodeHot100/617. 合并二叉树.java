第一版
执行结果：
解答错误
通过测试用例：
128 / 182
输入：
[1,3,2,5]
[2,1,3,null,4,null,7]
输出：
[3,4,5,5]
预期结果：
[3,4,5,5,4,null,7]
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null && root2 == null){
            //return root1;
        }
        else if(root1 == null){// && root2 != null
            root1 = root2;
        }
        else if(root2 == null){
        }
        else{
            root1.val += root2.val;
            mergeTrees(root1.left, root2.left);
            mergeTrees(root1.right, root2.right);
        }
        return root1;
    }
}

第二版
执行结果：
解答错误
通过测试用例：
32 / 182
输入：
[1,3,2,5]
[2,1,3,null,4,null,7]
输出：
[3,5,8,5,4,null,7]
预期结果：
[3,4,5,5,4,null,7]
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null || root2 == null)
            return root1 == null ? root2 : root1;
        root1.val += root2.val;
        
        if(root1.left != null && root2.left != null){
            root1.left.val += root2.left.val;
            mergeTrees(root1.left, root2.left);
        }
        else if(root1.left == null && root2.left == null){
            
        }
        else if(root1.left == null){
            root1.left = root2.left;
        }
        else if(root2.left == null){
            
        }
        if(root1.right != null && root2.right != null){
            root1.right.val += root2.right.val;
            mergeTrees(root1.right, root2.right);
        }
        else if(root1.right == null && root2.right == null){

        }
        else if(root1.right == null){
            root1.right = root2.right;
        }
        else if(root2.right == null){
            root2.right = root1.right;
        }
        return root1;
    }
}

第三版
执行结果：
通过

执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
38.4 MB
, 在所有 Java 提交中击败了
82.26%
的用户
通过测试用例：
182 / 182
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null || root2 == null)
            return root1 == null ? root2 : root1;
            
        root1.val += root2.val;

        if(root1.left != null && root2.left != null){
            //root1.left.val += root2.left.val;
            mergeTrees(root1.left, root2.left);
        }
        else if(root1.left == null && root2.left == null){
            
        }
        else if(root1.left == null){
            root1.left = root2.left;
        }
        else if(root2.left == null){
            
        }
        if(root1.right != null && root2.right != null){
            //root1.right.val += root2.right.val;
            mergeTrees(root1.right, root2.right);
        }
        else if(root1.right == null && root2.right == null){

        }
        else if(root1.right == null){
            root1.right = root2.right;
        }
        else if(root2.right == null){
            
        }
        return root1;
    }
}

在第一版中我就发现不用left和right是不行的，没法连接root1的左右指针到root2上
所以我引入了left right

#### 
题解如下很简单
原因是题目提及“你需要将他们合并为一个新的二叉树。”
所以新建一棵树是允许也是必要的，对空间复杂度的要求降低了
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = mergeTrees(t1.left, t2.left);
        merged.right = mergeTrees(t1.right, t2.right);
        return merged;
    }
}

#### 根据题解 也可以得到“不建立新的二叉树而能得到的结果”
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null)
            return root2;
        if(root2 == null)
            return root1;
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }
}
