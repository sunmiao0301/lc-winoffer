## 看了题解 学习思路 然后自己写出来了
## 这题太聪明了！
"我们应该这样思考：
逆向思考 --->
反序列化一个（不含重复值的）二叉树，我们需要哪些数据？ --->
需要一个树的 1）中序遍历 + 2）前序遍历和后序遍历中的任意一个 ---> 
但是二叉搜索树同时又是有规律的，中序遍历 = 前序遍历和后序遍历中的任意一个的排序后的结果（二叉搜索树的中序遍历递增）--->
所以我们只需要 前序遍历和后序遍历中的任意一个 --->
前序遍历（根左右）和后序遍历（左右根）有什么区别？ --->
没区别！选任意一个都行，但是需要注意，我们通过根直接就能分割出左右子树，不再需要对其进行排序得到中序遍历了！--->
所以我们在序列化和反序列化操作中传递的是后序遍历（或前序遍历）的字符串！
   
"题解思路
给定一棵二叉树的「先序遍历」和「中序遍历」可以恢复这颗二叉树。给定一棵二叉树的「后序遍历」和「中序遍历」也可以恢复这颗二叉树。而对于二叉搜索树，给定「先序遍历」或者「后序遍历」，对其经过排序即可得到「中序遍历」。因此，仅对二叉搜索树做「先序遍历」或者「后序遍历」，即可达到序列化和反序列化的要求。此题解采用「后序遍历」的方法。
序列化时，只需要对二叉搜索树进行后序遍历，再将数组编码成字符串即可。
反序列化时，需要先将字符串解码成后序遍历的数组。在将后序遍历的数组恢复成二叉搜索树时，不需要先排序得到中序遍历的数组再根据中序和后序遍历的数组来恢复二叉树，而可以根据有序性直接由后序遍历的数组恢复二叉搜索树。后序遍历得到的数组中，根结点的值位于数组末尾，左子树的节点均小于根节点的值，右子树的节点均大于根节点的值，可以根据这些性质设计递归函数恢复二叉搜索树。

"但是null值怎么解决？
不涉及！

执行结果：
通过
显示详情
添加备注

执行用时：
6 ms
, 在所有 Java 提交中击败了
79.31%
的用户
内存消耗：
42 MB
, 在所有 Java 提交中击败了
78.83%
的用户
通过测试用例：
62 / 62
  
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // 二叉搜索树 --> 我们可以简单的按照二叉树的数组表示方法 
    // 但是为什么题目说二叉搜索树呢 是搜索树相对于二叉树有什么更简单的方法嘛？
    // 看了题解之后发现，是的！因为297. 二叉树的序列化与反序列化是hard!
    // 二叉搜索树通过前序遍历和中序遍历即一定恢复原树 -- 我们选择用前序遍历写

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode tmp = stack.pop();
            if(tmp.right != null){
                stack.push(tmp.right);
            }
            if(tmp.left != null){
                stack.push(tmp.left);
            }
            sb.append(tmp.val);
            sb.append(',');
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == "") return null;
        String[] split = data.split(",");
        int len = split.length;
        int[] arr = new int[len];
        for(int i = 0; i < len; i++){
            arr[i] = Integer.parseInt(split[i]);
        }
        // return constructTree(arr, 0, len - 1);//双开区间
        /**
        根左右 -- 前序遍历
        TreeNode root = new TreeNode(arr[0]);
        root.left = new TreeNode(arr[1]);
        root.right = new TreeNode(arr[2]);
        return root;
        ---> input = output = [2,1,3]
        */
        return constructTree(arr, 0, len - 1);
        
    }

    public TreeNode constructTree(int[] arr, int left, int right){
        //参数为一个前序遍历二叉搜索树的结果
        if(left > right) return null;

        TreeNode root = new TreeNode(arr[left]);
        int leftBoundary = left + 1;
        while(leftBoundary <= right && arr[leftBoundary] < arr[left]){
            leftBoundary++;
        }
        root.left = constructTree(arr, left + 1, leftBoundary - 1);
        root.right = constructTree(arr, leftBoundary, right);
        return root;
    }
}
