第一版 自从在上一题中解决了pointer问题，现在递归写的漂亮多了 但是这一版还不对
执行结果：
解答错误
通过测试用例：
26 / 78
输入：
[3,1,4,null,2]
3
4
输出：
[3,2,4]
预期结果：
[3,null,4]
class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        //二叉搜索树
        //递归 然后找到小边界处，如果当前节点是小于就是了（等于都不行），然后将当前的节点的右子树替代其本身。大边界同理
        if(root == null){
            return null;
        }
        else if(root.val >= low && root.val <= high){
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
        }
        else if(root.val < low){
            return root.right;
        }
        else if(root.val > high){
            return root.left;
        }
        return root;
    }
}

第二版 通过
第一版的问题在于没有想清楚，比如low = 3, high = 4
那么此时如果root是3，则root左边的都小于3， 按道理说都不能要的，但是如果像我第一版那样写的话，就会把root.left.right（可能是个2）保留下来。
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
37.9 MB
, 在所有 Java 提交中击败了
77.08%
的用户
通过测试用例：
78 / 78
class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        //二叉搜索树
        //递归 然后找到小边界处，如果当前节点是小于就是了（等于都不行），然后将当前的节点的右子树替代其本身。大边界同理
        if(root == null){
            return null;
        }
        else if(root.val >= low && root.val <= high){
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
        }
        else if(root.val < low){
            return trimBST(root.right, low, high);
        }
        else if(root.val > high){
            return trimBST(root.left, low, high);
        }
        return root;
    }
}
