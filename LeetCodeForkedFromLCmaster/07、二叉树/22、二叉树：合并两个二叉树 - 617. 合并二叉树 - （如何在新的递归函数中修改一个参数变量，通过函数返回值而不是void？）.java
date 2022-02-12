2nd 关于这一题中需要注意的，请见这一题在主页上的readme备注。
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null || root2 == null){
            return root1 == null ? root2 : root1;
        }
        helper(root1, root2);
        root1.val += root2.val;
        return root1;
    }
    public void helper(TreeNode root1, TreeNode root2){
        if(root1.left != null && root2.left != null){
            root1.left.val += root2.left.val;
            helper(root1.left, root2.left);
        }
        else if(root1.left == null){
            root1.left = root2.left;
        }
        else{//if(root1.left != null && root2.left == null){

        }

        if(root1.right != null && root2.right != null){
            root1.right.val += root2.right.val;
            helper(root1.right, root2.right);
        }
        else if(root1.right == null){
            root1.right = root2.right;
        }
        else{ //if(root1.left != null && root2.left == null){
        }
    }
}
2nd
再优化 通过
root.left =
root.right =
摆脱了helper()函数的束缚，一个方法直接解决。
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null || root2 == null){
            return root1 == null ? root2 : root1;
        }
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);
        return root1;
    }
}



//第一版 没通过 应该还是老问题 递归外部函数没法对主函数内的变量（root1, root2）进行修改 明天再改（新建一个树 或者合并到主函数里面试试）考研室友睡觉了
输入
[1,3,2,5]
[2,1,3,null,4,null,7]
输出
[3,4,5,5]
预期结果
[3,4,5,5,4,null,7]
class Solution {
    /*
    合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值
    否则不为 NULL 的节点将直接作为新二叉树的节点。
    注意: 合并必须从两个树的根节点开始。
    */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        combine(root1, root2);
        return root2;
    }
    void combine(TreeNode root1, TreeNode root2){
        if(root1 != null && root2 != null){
            root1.val = root1.val + root2.val;
            combine(root1.left, root2.left);
            combine(root1.right, root2.right);
        }
        else if(root1 != null){
            root2 = new TreeNode(root1.val, null, null);
            combine(root1.left, root2.left);
            combine(root1.right, root2.right);
        }
        else if(root2 != null){
            root1 = new TreeNode(root2.val, null, null);
            combine(root1.left, root2.left);
            combine(root1.right, root2.right);
        }
    }
}

//第二版 调试样例后一遍过 但是效率一般 用的是一个全局变量实现的 想不到该如何在原有的树基础上实现 因为：
1）只用一个函数方法我想不到递归的思路来使得最后return root1/root2
2）加一个函数如果不建立新的全局变量（树） 调用的递归函数是无法对数组以外的参数变量进行修改的
执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
25.71%
的用户
内存消耗：
38.7 MB
, 在所有 Java 提交中击败了
37.86%
的用户
通过测试用例：
182 / 182
class Solution {
    /*
    合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值
    否则不为 NULL 的节点将直接作为新二叉树的节点。
    注意: 合并必须从两个树的根节点开始。
    */
    TreeNode mixTree= new TreeNode(0);
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 == null || root2 == null)
            return (root1 == null) ? root2 : root1;
        mixTree.val = root1.val + root2.val;//进入combine函数的mixTree节点都是有值的
        combine(root1, root2, mixTree);
        return mixTree;
    }
    void combine(TreeNode root1, TreeNode root2, TreeNode mixTree){
        if(root1.left != null && root2.left != null){
            mixTree.left = new TreeNode(root1.left.val + root2.left.val);
            combine(root1.left, root2.left, mixTree.left);
        }
        else if(root1.left != null && root2.left == null){
            mixTree.left = new TreeNode(root1.left.val);
            copyOne(root1.left, mixTree.left);
        }
        else if(root1.left == null && root2.left != null){
            mixTree.left = new TreeNode(root2.left.val);
            copyOne(root2.left, mixTree.left);
        }

        if(root1.right != null && root2.right != null){
            mixTree.right = new TreeNode(root1.right.val + root2.right.val);
            combine(root1.right, root2.right, mixTree.right);
        }
        else if(root1.right != null && root2.right == null){
            mixTree.right = new TreeNode(root1.right.val);
            copyOne(root1.right, mixTree.right);
        }
        else if(root1.right == null && root2.right != null){
            mixTree.right = new TreeNode(root2.right.val);
            copyOne(root2.right, mixTree.right);
        }
    }
    void copyOne(TreeNode root, TreeNode mixTree){
        if(root.left != null){
            mixTree.left = new TreeNode(root.left.val);
            copyOne(root.left, mixTree.left);
        }
        if(root.right != null){
            mixTree.right = new TreeNode(root.right.val);
            copyOne(root.right, mixTree.right);
        }
    }
}

//第三版 尝试在一棵树上实现 失败
已完成
执行用时：0 ms
输入
[1,4,2,5]
[2,1,3,null,4,null,7]
输出
[3,5,5,5]
预期结果
[3,5,5,5,4,null,7]
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if(root1 != null && root2 != null){
            root1.val = root1.val + root2.val;
            mergeTrees(root1.left, root2.left);
            mergeTrees(root1.right, root2.right);
        }
        if(root1 == null && root2 != null){
            root1 = new TreeNode(root2.val);
            mergeTrees(root1.left, root2.left);
            mergeTrees(root1.right, root2.right);
        }
        return root1;
    }
}

//标准答案 这就又引出一个问题 为什么他的递归函数能修改参数变量呢？ 因为他有返回值？ 这个问题值得思考和分析
class Solution {
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		if(t1 == null || t2 == null) {
			return t1 == null ? t2 : t1;
		}
		return dfs(t1,t2);
	}
	
	TreeNode dfs(TreeNode r1, TreeNode r2) {
		// 如果 r1和r2中，只要有一个是null，函数就直接返回
		if(r1 == null || r2 == null) {
			return r1 == null ? r2 : r1;
		}
		//让r1的值 等于  r1和r2的值累加，再递归的计算两颗树的左节点、右节点
		r1.val += r2.val;
		r1.left = dfs(r1.left, r2.left);
		r1.right = dfs(r1.right, r2.right);
		return r1;
	}
}
