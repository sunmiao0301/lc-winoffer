第一版
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
36.6 MB
, 在所有 Java 提交中击败了
63.84%
的用户
通过测试用例：
70 / 70
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        //中序遍历是深度遍历的一种 左根右
        //递归
        LinkedList<Integer> list = new LinkedList<Integer>();
        if(root == null)
            return list;
        midTraversal(root, list);
        return list;
    }
    void midTraversal(TreeNode root, LinkedList list){ //这里我用的传参 但是很奇怪为什么上一题 - 树的最深子节点却不行 太久没coding了
        if(root.left != null)
            midTraversal(root.left, list);
        list.add(root.val);
        if(root.right != null)
            midTraversal(root.right, list);
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
36.7 MB
, 在所有 Java 提交中击败了
49.26%
的用户
通过测试用例：
70 / 70
class Solution {
    LinkedList<Integer> list = new LinkedList<Integer>(); // 加个全局变量 而不用传参
    public List<Integer> inorderTraversal(TreeNode root) {
        //中序遍历是深度遍历的一种 左根右
        //递归
        if(root == null)
            return list;
        midTraversal(root);
        return list;
    }
    void midTraversal(TreeNode root){
        if(root.left != null)
            midTraversal(root.left);
        list.add(root.val);
        if(root.right != null)
            midTraversal(root.right);
    }
}

第三版 尝试写的迭代中序遍历
但是这里写错了 导致死循环
想一下下面情境就知道为什么会死循环了
    1
   / \
  2   3
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        //中序 用栈实现深度遍历 左根右
        //拿出栈的时候 看栈顶元素左是否空 + 看右是否空
        //如果左边不空 就把左边放进栈
        //如果右边不空 就把栈顶拿出 并放入右边进栈
        //重复直到栈空
        LinkedList<TreeNode> s = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null) return list;
        s.push(root);
        while(!s.isEmpty()){
            if(s.peek().left != null)
                s.push(s.peek().left);
            else if(s.peek().right != null){
                list.add(s.peek().val);
                s.push(s.pop().right);
            }
            else
                list.add(s.pop().val); 
        }
        return list;
    }
}



####
方法二：迭代
思路与算法
递归我们也可以用迭代的方式实现，两种方式是等价的
区别在于递归的时候隐式地维护了一个栈，而我们在迭代的时候需要显式地将这个栈模拟出来，其他都相同，具体实现可以看下面的代码。
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        // 栈 先进后出
        // 前序遍历，出栈顺序：根左右; 入栈顺序：右左根
        // 中序遍历，出栈顺序：左根右; 入栈顺序：右根左
        // 后序遍历，出栈顺序：左右根; 入栈顺序：根右左
        List<Integer> ans = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        // root为空且stack为空，遍历结束
        while (root != null || !stack.isEmpty()) {
            // 先根后左入栈
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 此时root==null，说明上一步的root没有左子树
            // 1. 执行左出栈。因为此时root==null，导致root.right一定为null
            // 2. 执行下一次外层while代码块，根出栈。此时root.right可能存在
            // 3a. 若root.right存在，右入栈，再出栈
            // 3b. 若root.right不存在，重复步骤2
            root = stack.pop();
            ans.add(root.val);
            root = root.right;
        }
        return ans;
    }
}

####
方法三：Morris 中序遍历
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        TreeNode predecessor = null;

        while (root != null) {
            if (root.left != null) {
                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
                predecessor = root.left;
                while (predecessor.right != null && predecessor.right != root) {
                    predecessor = predecessor.right;
                }
                
                // 让 predecessor 的右指针指向 root，继续遍历左子树
                if (predecessor.right == null) {
                    predecessor.right = root;
                    root = root.left;
                }
                // 说明左子树已经访问完了，我们需要断开链接
                else {
                    res.add(root.val);
                    predecessor.right = null;
                    root = root.right;
                }
            }
            // 如果没有左孩子，则直接访问右孩子
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}
