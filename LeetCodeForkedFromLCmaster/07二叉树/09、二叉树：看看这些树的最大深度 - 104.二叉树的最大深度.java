2nd
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left == null && root.right == null)
            return 1;
        else if(root.left == null && root.right != null)
            return 1 + maxDepth(root.right);
        else if(root.left != null && root.right == null)
            return 1 + maxDepth(root.left);        
        else//(root.left != null && root.right != null)
            return Math.max(1 + maxDepth(root.left), 1 + maxDepth(root.right));
    }
}
#### 2nd看的题解
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}


//第一版 用的是层序遍历 统计层数 虽然通过 但是时间复杂度很差
执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
19.00%
的用户
内存消耗：
38.3 MB
, 在所有 Java 提交中击败了
64.43%
的用户
通过测试用例：
39 / 39
class Solution {
    //感觉只有层序遍历才行？
    public int maxDepth(TreeNode root) {
        int depth = 0;//第一层是1
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null) return depth;
        queue.offer(root);
        depth++;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                TreeNode temp = queue.poll();
                if(temp.left != null)
                    queue.offer(temp.left);
                if(temp.right != null)
                    queue.offer(temp.right);
                size--;
            }
            depth++;
        }
        return depth - 1;//queue.isEmpty()的时候 还会执行一次depth++
    }
}

//第二版 一遍过 换成递归 时间复杂度好多了
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
38.3 MB
, 在所有 Java 提交中击败了
55.65%
的用户
通过测试用例：
39 / 39
class Solution {
    int depth = 0;
    public int maxDepth(TreeNode root) {
        if(root == null) return depth;
        helper(root, 1);
        return depth;
    }
    void helper(TreeNode root, int d){
        if(d > depth) depth = d;
        if(root.left != null)
            helper(root.left, d + 1);
        if(root.right != null)
            helper(root.right, d + 1);
        return;
    }
}

//第三版 但其实 第二版 不是最简单的求最大深度的 写法 如下
需要注意的是，之所以最大深度比最小深度写的简单 是因为：
1）最小深度和最大深度 都是在找一个点 那个点的left和right都是null
2）其中最小深度必须严格遵守这个left和right都为null 而不能用这个：
        if(root == null) 
            return num;
3）但是最大深度不一样 对于一个由上而下的递归 最终肯定有一条递归路线能达到一个最深的地方 所以无需遵守left和right都为null 返回就完事了 反正最后的max会选出最大的那个。所以可以用：
        if(root == null) 
            return num;
class Solution {
    public int maxDepth(TreeNode root) {
        return helper(root, 0);
    }
    int helper(TreeNode root, int num){//root, 0
        if(root == null) 
            return num;
        else
            return Math.max(helper(root.left, num + 1), helper(root.right, num + 1));
    }
}

//当然 还有更简答的写法 无需helper函数 但是难想到一点：
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = maxDepth(root.left);
            int rightHeight = maxDepth(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
