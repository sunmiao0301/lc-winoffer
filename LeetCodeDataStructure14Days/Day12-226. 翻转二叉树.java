## 未借助 helper() 函数，写出来了递归版的本题 -- 感觉不借助的话，一般写法格式就是：

1)边界处理
2)左右调换（递归） 
  -- left = func(...);
  -- right = func(...);
3)return something

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
38.8 MB
, 在所有 Java 提交中击败了
64.69%
的用户
通过测试用例：
77 / 77

class Solution {
    public TreeNode invertTree(TreeNode root) {
        //递归翻转 -- 树中节点数目范围在 [0, 100] 内
        if(root == null){
            return null;
        }
        // else{
        //     return root;
        // }
        TreeNode tmp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = tmp;

        return root;

    }
}
