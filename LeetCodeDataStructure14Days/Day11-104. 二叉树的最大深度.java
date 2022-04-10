## 第一版 -- 先写一版比较呆的写法
class Solution {
    int max = 0;
    public int maxDepth(TreeNode root) {
        helper(root, 0);
        return max;
    }
    public void helper();
}

## 第二版 修改后 实际上不需要那么呆的加一个helper()函数 递归的思想还是需要多思考 动态规划学完得抓紧写递归回溯
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        else if(root.left == null && root.right == null){
            return 1;
        }
        // if(root.left == null){
        //     return maxDepth(root.right) + 1;
        // }
        // else if(root.right == null){
        //     return maxDepth(root.left) + 1;
        // }
        else{
            return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
        }
    }
}

## 题解
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
