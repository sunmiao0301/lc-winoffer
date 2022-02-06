2nd
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<>();
        //树中节点的数目在范围 [1, 100] 内
        //StringBuilder sb = new StringBuilder();
        helper(root, new StringBuilder(), res);
        return res;
    }
    public void helper(TreeNode root, StringBuilder sb, List<String> res){
        sb.append(root.val + "->");
        if(root.left == null && root.right == null){
            sb.delete(sb.length() - 2, sb.length());
            res.add(sb.toString());
        }
        else{
            if(root.left != null)
                helper(root.left, new StringBuilder(sb.toString()), res);
            if(root.right != null)
                helper(root.right, new StringBuilder(sb.toString()), res);
        }
    }
}

//第一版 通过 但是效率不高
执行结果：
通过
执行用时：
8 ms
, 在所有 Java 提交中击败了
41.29%
的用户
内存消耗：
38.8 MB
, 在所有 Java 提交中击败了
13.46%
的用户
通过测试用例：
208 / 208
class Solution {
    /*
    叶子节点 是指没有子节点的节点。
    肯定用递归做
    */
    List<String> list = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if(root == null)return list;
        write(root, "");
        return list;
    }
    void write(TreeNode root, String s){
        if(root == null)
            return;
        if(root.left == null && root.right == null){
            s = s + root.val;
            list.add(s);
        }
        else{
            s = s + root.val + "->";
            if(root.left != null)
                write(root.left, s);
            if(root.right != null)
                write(root.right, s);            
        }
    }
}

//最快答案 果然 涉及字符串的时候 想要提升速度 问题不在于算法 而在于用法
执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
38.2 MB
, 在所有 Java 提交中击败了
93.72%
的用户
通过测试用例：
208 / 208
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<String>();
        dfs(root,"",res);
        return res;
    }
    public void dfs(TreeNode node, String path, List res){
        if(node == null){
            return;
        }
        StringBuffer str = new StringBuffer(path);
        str.append(node.val);
        if(node.left == null && node.right == null){
            res.add(str.toString());
        }else{
            str.append("->");
            dfs(node.left,str.toString(),res);
            dfs(node.right,str.toString(),res);
        }
    }
}
