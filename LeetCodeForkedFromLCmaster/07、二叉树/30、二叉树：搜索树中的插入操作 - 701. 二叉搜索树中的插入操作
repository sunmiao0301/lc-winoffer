## 二刷 如果不通过 root.left = func(..);的写法的话，是没法在最后返回最后的root值的，如下（只能通过一个多余的helper函数来实现）但是时间还是达到了100%
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
        //二叉搜索树插入一定能插在叶子节点上
        if(root == null)
            return new TreeNode(val);
        helper(root, val);
        return root;
    }
    public void helper(TreeNode root, int val){
        if(val < root.val && root.left != null)
            helper(root.left, val);
        else if(val < root.val && root.left == null)
            root.left = new TreeNode(val);
        else if(val > root.val && root.right != null)
            helper(root.right, val);
        else if(val > root.val && root.right == null)
            root.right = new TreeNode(val);
    }
}

## 二刷 不借助helper函数
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null)
            return new TreeNode(val);

        if(val < root.val)
            root.left = insertIntoBST(root.left, val);
        else if(val > root.val)
            root.right = insertIntoBST(root.right, val);
        return root;
    }
}
p.s. 注意这种写法下，需要理解这个root.left = insertIntoBST(root.left, val);
我的理解是每一层递归中，都有一个这样的赋值（其实也是重复赋值了），但是最后返回的都是new TreeNode，所以哪怕出现的重复赋值，最后也是没问题的。



#### 看了题解 写出的错误版本
执行结果：
解答错误
通过测试用例：
22 / 35
输入：
[4520,330,9882,284,3585,9800,9975,105,null,1543,3663,4550,9816,null,null,null,null,593,1999,3622,3979,null,8559,null,null,511,1374,1877,3171,null,null,3669,4370,6337,8694,null,null,1178,1417,1851,null,2303,3226,null,3941,4162,4446,4750,7496,8593,9236,879,1350,null,null,1633,null,2207,3087,3213,3259,null,null,null,null,null,null,null,5379,6372,7860,8586,8654,9058,9422,622,1131,1255,null,1578,1839,2177,null,2510,3142,null,null,null,3452,5001,5811,null,7435,7858,8146,null,null,null,null,null,null,9295,9555,601,null,1028,null,null,null,null,null,null,null,null,null,2333,3005,null,null,3392,null,null,5245,5697,6074,7337,null,7497,null,7948,8473,null,null,null,null,null,null,null,null,null,2351,2634,3086,3273,null,5222,5246,5481,5756,5947,6299,6600,7353,null,null,null,null,8199,8558,null,null,null,2966,null,null,null,null,5056,null,null,null,null,5582,null,null,null,6030,null,null,6518,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,6430,6533,null,null,null,null]
2970
输出：
[4520,330,9882,284,3585,9800,9975,105,null,1543,3663,4550,9816,null,null,null,null,593,1999,3622,3979,null,8559,null,null,511,1374,1877,3171,null,null,3669,4370,6337,8694,null,null,1178,1417,1851,null,2303,3226,null,3941,4162,4446,4750,7496,8593,9236,879,1350,null,null,1633,null,2207,3087,3213,3259,null,null,null,null,null,null,null,5379,6372,7860,8586,8654,9058,9422,622,1131,1255,null,1578,1839,2177,null,2510,3142,null,null,null,3452,5001,5811,null,7435,7858,8146,null,null,null,null,null,null,9295,9555,601,null,1028,null,null,null,null,null,null,null,null,null,2333,3005,null,null,3392,null,null,5245,5697,6074,7337,null,7497,null,7948,8473,null,null,null,null,null,null,null,null,null,2351,2634,3086,3273,null,5222,5246,5481,5756,5947,6299,6600,7353,null,null,null,null,8199,8558,null,null,null,2966,2970,null,null,null,5056,null,null,null,null,5582,null,null,null,6030,null,null,6518,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,6430,6533]
预期结果：
[4520,330,9882,284,3585,9800,9975,105,null,1543,3663,4550,9816,null,null,null,null,593......
class Solution {
    public ArrayList<TreeNode> array = new ArrayList<>();
    public int len = 0;
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null)
            return new TreeNode(val);
        //只插入一个数的话 感觉难度还好
        dfs(root);
        for(int i = 0; i < len; i++){
            if(val < array.get(i).val){
                array.get(i).left = new TreeNode(val);
                break;
            }
            if(i == len - 1)
                array.get(i).right = new TreeNode(val);
        }
        return root;
    }
    public void dfs(TreeNode root){
        if(root.left != null)
            dfs(root.left);
        if(root.left == null || root.right == null){
            array.add(root);
            len++;
        }
        if(root.right != null)
            dfs(root.right);
    }
}

#### 看了题解思路（不是代码）之后自己写的

其实想清楚一点就能很轻松的写出来递归版，那就是搜索二叉树，左子树的所有点都比根节点小，右子树所有节点都比根节点大，那么按照这个规律，递归的帮要插入的新节点找个位置就行了。

执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
39 MB
, 在所有 Java 提交中击败了
72.61%
的用户
通过测试用例：
35 / 35
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root == null)
            return new TreeNode(val);
        helper(root, val);
        return root;
    }
    public void helper(TreeNode root, int val){
        if(val < root.val && root.left != null){
            insertIntoBST(root.left, val);
        }
        else if(val < root.val && root.left == null){
            root.left = new TreeNode(val);
        }
        else if(val > root.val && root.right != null){
            insertIntoBST(root.right, val);
        }
        else if(val > root.val && root.right == null){
            root.right = new TreeNode(val);  
        }
    }
}
