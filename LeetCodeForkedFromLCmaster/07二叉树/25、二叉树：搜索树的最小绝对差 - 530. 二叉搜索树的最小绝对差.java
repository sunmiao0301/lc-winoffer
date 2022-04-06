2nd
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
    public int getMinimumDifference(TreeNode root) {
        //一样的道理，中序遍历，然后找前后相邻两个差值的绝对值最小值
        //左根右
        int[] min = new int[]{Integer.MAX_VALUE};
        int[] preValue = new int[]{Integer.MAX_VALUE};
        TreeNode left = findLeft(root);
        dfs(root, min, preValue, left);
        return min[0];
    }
    public void dfs(TreeNode root, int[] min, int[] preValue, TreeNode left){
        if(root.left != null)
            dfs(root.left, min, preValue, left);
        if(root != left){
            int tmp = Math.abs(preValue[0] - root.val);
            if(tmp < min[0])
                min[0] = tmp;
        }
        preValue[0] = root.val;
        if(root.right != null)
            dfs(root.right, min, preValue, left);
    }
    public TreeNode findLeft(TreeNode root){
        if(root.left != null)
            return findLeft(root.left);
        else
            return root;
    }
}

//第一版 想的太简单了 最近刷的少了 考虑问题都变简单了
直接被样例打回：
执行结果：
解答错误
通过测试用例：
127 / 188
输入：
[236,104,701,null,227,null,911]
输出：
123
预期结果：
9
class Solution {
    int min = Integer.MAX_VALUE;
    public int getMinimumDifference(TreeNode root) {
    //要是最大值就好了 但是不行 那么比较每两个上下相邻的结点值即可
    //树中至少有 2 个节点。
    helper(root);
    return min;
    }
    void helper(TreeNode root){
        if(root.left != null){
            min = root.val - root.left.val < min ? root.val - root.left.val : min;
            helper(root.left);
        }
        if(root.right != null){
            min = root.right.val - root.val < min ? root.right.val - root.val : min;
            helper(root.right);
        }
    }
}

//第二版 室友睡觉 明天再做
class Solution {
    public int getMinimumDifference(TreeNode root) {
    //感觉和按标题刷的时候的 上一题是一样的思路
    //所有结点都是非负值 这个条件怎么用上？
    //为什么是差的绝对值的最小值 绝对值意义何在？
    //想到那句话：搜索二叉树的中序遍历是升序排列 可以用这个 左根右->栈实现
    }
}

//第三版 没有用栈实现 直接用的递归
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
38.1 MB
, 在所有 Java 提交中击败了
68.18%
的用户
通过测试用例：
188 / 188
炫耀一下:
class Solution {
    int min = Integer.MAX_VALUE;
    int bef;
    int flag = 1;
    public int getMinimumDifference(TreeNode root) {
    //感觉和按标题刷的时候的 上一题是一样的思路
    //所有结点都是非负值 这个条件怎么用上？ 没发现咋用上，找到用法了！！！非负数使得flag可以和bef用一个变量存就行了
    //为什么是差的绝对值的最小值 绝对值意义何在？ 意义在于你要知道搜索二叉树各个相邻结点之间的大小关系
    //想到那句话：搜索二叉树的中序遍历是升序排列 可以用这个 左根右->栈实现 但是明显还是递归简单
        dfs(root);
        return min; 
    }
    void dfs(TreeNode root){
        if(root.left != null){
            dfs(root.left);
        }
        if(flag == 1){
            flag = 0;
            bef = root.val;
        }
        else{
            if(root.val - bef < min)
                min = root.val - bef;
            bef = root.val;
        }
        if(root.right != null){
            dfs(root.right);
        }
    }
}

//实际上我这种写法还浪费了一个 int 的空间 几天没写思维变差了 flag其实可以和bef用一个存
class Solution {
    int pre;
    int ans;

    public int getMinimumDifference(TreeNode root) {
        ans = Integer.MAX_VALUE;
        pre = -1;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (pre == -1) {
            pre = root.val;
        } else {
            ans = Math.min(ans, root.val - pre);
            pre = root.val;
        }
        dfs(root.right);
    }
}
复杂度分析
时间复杂度：O(n)O(n)，其中 nn 为二叉搜索树节点的个数。每个节点在中序遍历中都会被访问一次且只会被访问一次，因此总时间复杂度为 O(n)O(n)。
空间复杂度：O(n)O(n)。递归函数的空间复杂度取决于递归的栈深度，而栈深度在二叉搜索树为一条链的情况下会达到 O(n)O(n) 级别。
