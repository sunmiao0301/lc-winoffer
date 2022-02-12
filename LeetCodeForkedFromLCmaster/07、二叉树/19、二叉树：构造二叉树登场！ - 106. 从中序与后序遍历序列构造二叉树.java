2nd 这样写 跑通了一部分
但是这种例子跑不通：
执行结果：
解答错误
显示详情
添加备注

通过测试用例：
3 / 202
输入：
[2,1]
[2,1]
输出：
[2]
预期结果：
[1,2]
class Solution {
    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        //left - root - right
        //left - right - root
        /**
         * [2,1,3]
         * [2,3,1]
         */
        TreeNode root = new TreeNode(inorder[0]);// = new TreeNode(0);
        int len = inorder.length;
        for(int i = 0; i < len; i++){
            if(inorder[i] != postorder[i]){
                root = new TreeNode(inorder[i]);
                root.left = buildLeft(inorder, postorder, 0, i, 0, i);//[, )
                root.right = buildRight(inorder, postorder, i + 1, len, i, len - 1);
                break;
            }
        }
        return root;
    }
    public static TreeNode buildLeft(int[] inorder, int[] postorder, int beginOfIn, int endOfIn, int beginOfPost, int endOfPost){

        TreeNode root = new TreeNode(inorder[beginOfIn]);
        for(int i = 0; i < endOfIn - beginOfIn; i++){
            if(inorder[i + beginOfIn] != postorder[i + beginOfPost]){
                root = new TreeNode(inorder[i + beginOfIn]);
                root.left = buildLeft(inorder, postorder, beginOfIn, beginOfIn + i, beginOfPost, beginOfPost + i);
                root.right = buildRight(inorder, postorder, i + beginOfIn + 1, endOfIn, i + beginOfPost, endOfPost - 1);
                break;
            }
        }
        return root;
    }
    public static TreeNode buildRight(int[] inorder, int[] postorder, int beginOfIn, int endOfIn, int beginOfPost, int endOfPost){
        TreeNode root = new TreeNode(postorder[beginOfPost]);//postorder[beginOfPost]
        for(int i = 0; i < endOfIn - beginOfIn; i++){
            if(inorder[i + beginOfIn] != postorder[i + beginOfPost]){
                root = new TreeNode(inorder[i + beginOfIn]);
                root.left = buildLeft(inorder, postorder, beginOfIn, beginOfIn + i, beginOfPost, beginOfPost + i);
                root.right = buildRight(inorder, postorder, i + beginOfIn + 1, endOfIn, i + beginOfPost, endOfPost - 1);
                break;
            }
        }
        return root;
    }
}

2nd
看了下题解 是因为我分割的办法出问题，我是根据
        //left - root - right
        //left - right - root
然后根据中序的root和后序的right来分割，但是这样不对，因为right不一定总是存在
但正确的方法应该是通过先找到后序中的root，然后再到中序里面通过root分割，因为root是一定存在的。
