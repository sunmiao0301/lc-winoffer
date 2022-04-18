## 感觉对树的遍历 如果用递归 问题就在于:
1)如果有除遍历以外的其他需求，我们可能还得加上全局变量(当然有些情况是可以用参数传递的，但是有些情况用参数传递也不合适，还是得借助全局变量 -- 比如参数是基本数据类型而不是引用类型的时候） 
  这一部门可以做个总结 -- 关于算法题的递归写法和迭代写法的优势。
2)此外还得有一个helper()函数

class Solution {
    
    HashSet<Integer> set = new HashSet<>();

    public boolean findTarget(TreeNode root, int k) {
        // 遍历一遍 -104 <= Node.val <= 104 所以没法剪枝 
        // 二叉搜索树 root 但是奇怪的一点在于 如果就遍历一遍的话 就没有用上我们二叉搜索树的性质 但是感觉用上性质的话 效率反而会差？
        // HashSet<Integer> set = new HashSet<>();
        return helper(root, k);
    }
    public boolean helper(TreeNode root, int k){
        if(root == null)return false;
        set.add(root.val);
        if(set.contains(k - root.val) && (k - root.val) != root.val){
            return true;
        }
        return helper(root.left, k) || helper(root.right, k);
    }
}

## 自己优化一下
class Solution {
    HashSet<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        // 遍历一遍 -104 <= Node.val <= 104 所以没法剪枝 
        // 二叉搜索树 root 但是奇怪的一点在于 如果就遍历一遍的话 就没有用上我们二叉搜索树的性质 但是感觉用上性质的话 效率反而会差？
        if(root == null)return false;
        if(set.contains(k - root.val)){
            return true;
        }
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }
}

## 我的递归版本于题解一致 但是问题来了 怎么才能用上二叉搜索树的性质？ -- 题解居然是把二叉搜索树中序遍历下来放到ArrayList里面，然后双指针找和为 k 如下
class Solution {
    List<Integer> list = new ArrayList<Integer>();

    public boolean findTarget(TreeNode root, int k) {
        inorderTraversal(root);
        int left = 0, right = list.size() - 1;
        while (left < right) {
            if (list.get(left) + list.get(right) == k) {
                return true;
            }
            if (list.get(left) + list.get(right) < k) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    public void inorderTraversal(TreeNode node) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left);
        list.add(node.val);
        inorderTraversal(node.right);
    }
}
