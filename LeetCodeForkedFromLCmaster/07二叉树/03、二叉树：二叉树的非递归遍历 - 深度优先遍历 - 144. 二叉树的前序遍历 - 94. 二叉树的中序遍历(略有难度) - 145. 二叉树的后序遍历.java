2nd
先序遍历 迭代法
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        //根左右
        //递归算法很简单，你可以通过迭代算法完成吗？
        //队列× 应该是栈
        LinkedList<TreeNode> stack = new LinkedList<>();
        List<Integer> res = new LinkedList<>();
        if(root == null) return res;

        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode tmp = stack.pop();
            res.add(tmp.val);
            if(tmp.right != null)
                stack.push(tmp.right);
            if(tmp.left != null)
                stack.push(tmp.left);
        }
        return res;
    }
}

后序遍历 迭代法
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        //左右根
        LinkedList<TreeNode> stack = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        if(root == null) return res;
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode tmp = stack.pop();
            res.addFirst(tmp.val);
            if(tmp.left != null)
                stack.push(tmp.left);
            if(tmp.right != null)
                stack.push(tmp.right);
        }
        return res;
    }
}



/*
首先还是二叉树的定义来一遍 
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

//然后开始非递归法写三种深度优先遍历
//辅助栈 先序遍历 根左右 还是根据力扣里面的要求来吧 - LeetCode 144
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
36.8 MB
, 在所有 Java 提交中击败了
34.35%
的用户
通过测试用例：
69 / 69
class Solution {
    //根左右
    public List<Integer> preorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    List<Integer> ret = new ArrayList<>();
    if(root == null)
        return ret;
    stack.push(root);
    while(!stack.isEmpty()){
        TreeNode temp = stack.pop();
        if(temp.right != null)
            stack.push(temp.right);
        if(temp.left != null)
            stack.push(temp.left);
        ret.add(temp.val);
    }
    return ret;
    }
}

//辅助栈 后序遍历 左右根 还是根据力扣里面的要求来吧 - LeetCode 145
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
36.7 MB
, 在所有 Java 提交中击败了
48.77%
的用户
通过测试用例：
68 / 68
class Solution {
    //首先分析一下后序遍历与先序遍历的区别在于？ 区别不大 思路是完全一致的 放入的顺序问题 非递归法深度遍历的难度在于中序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList<>();
        /*
        这里需要注意一下，如果是只用add，offer等函数，直接list<> list = new LinkedList/ArrayList<>();即可
        但是如果要用addFirst,offerFirst等函数，还是LinkedList<> list = new LinkedList/ArrayList<>();才行
        综上，LinkedList<Integer> list = new LinkedList/ArrayList<>();是最为稳妥的
        */
        Stack<TreeNode> stack = new Stack<>();
        if(root == null)return list;
        stack.push(root);
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            list.offerFirst(temp.val);
            if(temp.left != null)
                stack.push(temp.left);
            if(temp.right != null)
                stack.push(temp.right);
        }
        return list;
    }
}

//辅助栈 中序遍历 左根右 还是根据力扣里面的要求来吧 - LeetCode 94
这一题中序遍历非递归法思考的时候感觉用迭代法还是有点难度的，主要在于只能用栈。
非递归法深度遍历的难度在于中序遍历

没转过来 就是想不到 下面是标准题解之一
实际上迭代法的非中序遍历的核心在于一句话：
递归的调用过程是不断往左边走，当左边走不下去了，就打印节点，并转向右边，然后右边继续这个过程。
只要理解了中序遍历的 左 根 右 的核心是这句话 就不难实现了
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    /*
    核心思路是一直到左（中的左）左不动了就入中 然后以中的右为中继续
    */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while(!stack.isEmpty() || root != null){
            if(root != null){
                stack.push(root);
                root = root.left;
            }
            else{
                TreeNode temp = stack.pop();
                list.add(temp.val);
                root = temp.right;
            }
        }
        return list;
    }
}
