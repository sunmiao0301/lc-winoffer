//第一版 执行出错 就感觉写的时候太简单了 不太对的上中等难度 果然不对
出错原因是 对二叉搜索树还是理解的不够好 二叉搜索树不是要求当前节点的左节点小于当前节点，而是要求当前节点的左子树内的节点都小于当前节点。
执行结果：
解答错误
通过测试用例：
72 / 80
输入：
[5,4,6,null,null,3,7]
输出：
true
预期结果：
false
class Solution {
    public boolean isValidBST(TreeNode root) {
        if(root.left != null && root.right != null){
            if(root.left.val < root.val && root.right.val > root.val)
                return isValidBST(root.left) && isValidBST(root.right);
            else
                return false;
        }
        else if(root.left != null && root.right == null){
            if(root.left.val < root.val)    
                return isValidBST(root.left);
            return false;
        }
        else if(root.left == null && root.right != null){
            if(root.right.val > root.val)
                return isValidBST(root.right);
            return false;
        }
        else
            return true;
    }
}

//第二版 虽然解决了上一个错误 但是又有一个新错误例 明天再搞 室友睡觉
执行结果：
解答错误
通过测试用例：
16 / 80
输入：
[120,70,140,50,100,130,160,20,55,75,110,119,135,150,200]
输出：
true
预期结果：
false
class Solution {
    public boolean isValidBST(TreeNode root) {
    /*
    再加个参数 根节点的右子树节点的左节点需要大于根节点 那么就加个参数用于存储？或者不加，直接全局变量
    同理，根节点的左子树节点的右节点需要小于根节点
    不能用全局变量 因为会进行多个递归 还得用参数
    应该是用两个参数？因为是两层的就换一下
    */
    //if(root == null) return true;
    if(root.left != null && root.right != null){
        if(root.left.val < root.val && root.right.val > root.val)
            return helper(root.left, root.val, false) && helper(root.right, root.val, true);
        return false;
    }
    else if(root.left != null && root.right == null){
        if(root.left.val < root.val)
            return helper(root.left, root.val, false);
        return false;
    }
    else if(root.left == null && root.right != null){
        if(root.right.val > root.val)
            return helper(root.right, root.val, true);
        return false;
    }
    else
        return true;
    }
    boolean helper(TreeNode root, int bef, boolean isRight){
        if(root.left != null && root.right != null){
            if(isRight){
                if(root.left.val < root.val && root.left.val > bef && root.right.val > root.val)
                    return helper(root.left, root.val, false) && helper(root.right, root.val, true);
                return false;
            }
            else{
                if(root.right.val > root.val && root.right.val < bef && root.left.val < root.val)
                    return helper(root.left, root.val, false) && helper(root.right, root.val, true);
                return false;
            }
        }
        else if(root.left != null && root.right == null){
            if(isRight){
                if(root.left.val < root.val && root.left.val > bef)
                    return helper(root.left, root.val, false);
                return false;
            }
            else{
                if(root.left.val < root.val)
                    return helper(root.left, root.val, false);
                return false;
            }
        }
        else if(root.left == null && root.right != null){
            if(isRight){
                if(root.right.val > root.val)
                    return helper(root.right, root.val, true);
                return false;
            }
            else{
                if(root.right.val > root.val && root.right.val < bef)
                    return helper(root.left, root.val, false);
                return false;
            }            
        }
        else 
            return true;
    }
}

//第三版 错在了这个样例：[120,70,140,50,100,130,160,20,55,75,110,119,135,150,200]
看到这个样例的时候 我发现问题更复杂了 至于为什么复杂 可视化一下树就明白了 基本上每个节点都要单独考虑一下
在此基础上 我构思了一下新的思路 可以建立多个返回boolean的函数然后&&连接来判断 每一个都只判断一个大于或者小于关系 这样代码就比较自由
但是这样应该是要做非常多的比较和重复计算 肯定不是最佳办法
想想怎么优化：
对每个节点单独判断 每个节点进行的函数维护两个值 一个是大于的 一个是要小于的 
如果是来自于上一个节点的左子树 那么就更新“要小于的”
如果是来自于上一个节点的右子树 那么就更新“要大于的”
这个两个参数“要小于的”“要大于的”的初始值分别是JAVA允许的最大值和最小值。
想到这里 我就已经有答案了
但是没想到还有一个样例我处理不了

执行结果：
解答错误
通过测试用例：
72 / 80
输入：
[2147483647]
输出：
false
预期结果：
true
class Solution {
    public boolean isValidBST(TreeNode root) {
        int big = Integer.MIN_VALUE, small = Integer.MAX_VALUE;
        return helper(root, big, small);
    }
    boolean helper(TreeNode root, int big, int small){
        if(root == null)
            return true;
        if(root.val < small && root.val > big)
            return helper(root.left, big, root.val) && helper(root.right, root.val, small);
        else
            return false;
    }
}

//第四版 于是我又换了一版 又来了新的错样例。。。 软件测试工程师确实重要 solute
执行结果：
解答错误
通过测试用例：
74 / 80
输入：
[-2147483648,null,2147483647]
输出：
false
预期结果：
true
class Solution {
    public boolean isValidBST(TreeNode root) {
        int big = Integer.MIN_VALUE, small = Integer.MAX_VALUE;
        if(root.left == null && root.right == null) return true;
        return helper(root, big, small);
    }
    boolean helper(TreeNode root, int big, int small){
        if(root == null)
            return true;
        if(root.val < small && root.val > big)
            return helper(root.left, big, root.val) && helper(root.right, root.val, small);
        else
            return false;
    }
}

//第五版 看了下样例中节点值的范围 直接用long 拿下
//-231 <= Node.val <= 231 - 1
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
38.2 MB
, 在所有 Java 提交中击败了
11.27%
的用户
通过测试用例：
80 / 80
class Solution {
    public boolean isValidBST(TreeNode root) {
        //int big = Integer.MIN_VALUE, small = Integer.MAX_VALUE;
        long big = Long.MIN_VALUE, small = Long.MAX_VALUE;
        return helper(root, big, small);
    }
    boolean helper(TreeNode root, long big, long small){
        if(root == null)
            return true;
        if(root.val < small && root.val > big)
            return helper(root.left, big, root.val) && helper(root.right, root.val, small);
        else
            return false;
    }
}

//标准题解之 迭代遍历法
因为 中序遍历下的二叉搜索树结果是升序！！！！

方法二：中序遍历
思路和算法
基于方法一中提及的性质，我们可以进一步知道二叉搜索树「中序遍历」得到的值构成的序列一定是升序的，这启示我们在中序遍历的时候实时检查当前节点的值是否大于前一个中序遍历到的节点的值即可。如果均大于说明这个序列是升序的，整棵树是二叉搜索树，否则不是，下面的代码我们使用栈来模拟中序遍历的过程。
可能由读者不知道中序遍历是什么，我们这里简单提及一下，中序遍历是二叉树的一种遍历方式，它先遍历左子树，再遍历根节点，最后遍历右子树。而我们二叉搜索树保证了左子树的节点的值均小于根节点的值，根节点的值均小于右子树的值，因此中序遍历以后得到的序列一定是升序序列。

class Solution {
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
              // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
}
