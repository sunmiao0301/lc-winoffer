## 第一版 迭代法

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
41.4 MB
, 在所有 Java 提交中击败了
77.59%
的用户
通过测试用例：
36 / 36

class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        //root 是二叉搜索树
        while(root != null){
            if(root.val == val){
                return root;
            }
            else if(root.val > val){
                return searchBST(root.left, val);
            }
            else if(root.val < val){
                return searchBST(root.right, val);
            }
        }
        return null;
    }
}

## 第二版 递归法

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
41.2 MB
, 在所有 Java 提交中击败了
82.14%
的用户
通过测试用例：
36 / 36

class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if(root == null) return null;
        else if(root.val == val) return root;
        else if(root.val > val) return searchBST(root.left, val);
        else return searchBST(root.right, val);
    }
}
