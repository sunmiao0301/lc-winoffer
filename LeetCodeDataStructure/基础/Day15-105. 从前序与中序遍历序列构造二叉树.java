## 第一版 速度还行 思路是：
（1）在前序节点中直接拿到根节点 -- preorder[preLeft]
（2）通过中序节点中根节点的位置，将中序分割成左 中 右 三个部分，并且能得到左右子树的长度。
（3）得到左右子树的长度，然后再在前序中得到 中 左 右 三个部分
（4）通过第一步得到的根节点构造 TreeNode ret = new TreeNode(preorder[preLeft]);
（5）为ret的左右子树递归构造
        ret.left = helper(preorder, preLeft + 1, preLeft + 1 + i - inLeft - 1, inorder, inLeft, inLeft + i - inLeft - 1);
        ret.right = helper(preorder, preLeft + 1 + i - inLeft, preRight, inorder, i + 1, inRight);
（6）返回ret

执行结果：
通过
显示详情
添加备注

执行用时：
3 ms
, 在所有 Java 提交中击败了
54.09%
的用户
内存消耗：
41.4 MB
, 在所有 Java 提交中击败了
16.56%
的用户
通过测试用例：
203 / 203

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        //前序 -- 根左右 -- 前序的 左的左右 和 右的左右
        //中序 -- 左根右 -- 中序的 左的左右 和 右的左右
        //感觉这一题和根据有序数组构造二叉搜索树有异曲同工之妙

        return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }
    public TreeNode helper(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight){

        if(preLeft > preRight){
            return null;
        }

        TreeNode ret = new TreeNode(preorder[preLeft]);
              
        // int i = 0; //i从中序的左端开始 最终将指向inorder中根节点的位置
        // i 是 中序 根对应的位置 那么 右的头就是 i+1
        // for(i = 0; i < inorder.length; i++){
        //     if(inorder[i] == preorder[0]){//preorder的左边界是preorder[0]
        //         break;
        //     }
        // }
        
        int i = inLeft;
        for(i = inLeft; i <= inRight; i++){
            if(inorder[i] == preorder[preLeft]){
                break;
            }
        }
        //那么左子树一共就是i - inLeft 个
        //所以
        //preorder中的左就是 ： preLeft + 1 ~ preLeft + 1 + i - inLeft - 1
        //inorder中的左就是：inLeft ~ inLeft + i - inLeft - 1

        //preorder中的右就是：preLeft + 1 + i - inLeft ~ preRight
        //inorder中的右就是：i + 1 ~ inRight

        // int j = preLeft;
        // for(j = preLeft; j <= preRight; j++){
        //     if(preorder[j] == inorder[i + 1]){
        //         break;
        //     }
        // }

        ret.left = helper(preorder, preLeft + 1, preLeft + 1 + i - inLeft - 1, inorder, inLeft, inLeft + i - inLeft - 1);
        ret.right = helper(preorder, preLeft + 1 + i - inLeft, preRight, inorder, i + 1, inRight);

        return ret;
    }
}

## 题解 思路是一样的 但是速度比我快 因为用的是哈希表

class Solution {
    private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);
        
        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }
}

