2nd 100% 但是不够优雅
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        return helper(root, targetSum, 0);
    }
    public boolean helper(TreeNode root, int targetSum, int currentSum){
        if(root == null)
            return false;
        else if(root.left == null && root.right == null && currentSum + root.val == targetSum)
            return true;
        else{
            return helper(root.left, targetSum, currentSum + root.val) || helper(root.right, targetSum, currentSum + root.val);
        }
    }
}
#### 想要优雅 肯定不能传参currentSum,所以得反过来减去 
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return sum == root.val;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}

//第一版 差点一遍过 被测试样例坑了
执行结果：
解答错误
通过测试用例：
116 / 117
输入：
[]
0
输出：
true
预期结果：
false
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        //树中节点的数目在范围 [0, 5000] 内
        if(root == null){
            if(targetSum == 0)
                return true;
            return false;
        }
        return search(root, targetSum);
    }
    Boolean search(TreeNode root, int sum){
        if(root.left == null && root.right == null){
            if(sum - root.val == 0)
                return true;
            return false;
        }
        else if(root.left == null)
            return search(root.right, sum - root.val);
        else if(root.right == null)
            return search(root.left, sum - root.val);
        else
            return search(root.right, sum - root.val) || search(root.left, sum - root.val);
    }
}

//第二版 修改后 一遍过
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
54.52%
的用户
通过测试用例：
117 / 117
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        //树中节点的数目在范围 [0, 5000] 内
        if(root == null) return false;
        return search(root, targetSum);
    }
    Boolean search(TreeNode root, int sum){
        if(root.left == null && root.right == null){
            if(sum - root.val == 0)
                return true;
            return false;
        }
        else if(root.left == null)
            return search(root.right, sum - root.val);
        else if(root.right == null)
            return search(root.left, sum - root.val);
        else
            return search(root.right, sum - root.val) || search(root.left, sum - root.val);
    }
}

//此外这题还可以用BPS
对比一下复杂度：
递归
复杂度分析
时间复杂度：O(N)O(N)，其中 NN 是树的节点数。对每个节点访问一次。
空间复杂度：O(H)O(H)，其中 HH 是树的高度。空间复杂度主要取决于递归时栈空间的开销，最坏情况下，树呈现链状，空间复杂度为 O(N)O(N)。平均情况下树的高度与节点数的对数正相关，空间复杂度为 O(\log N)O(logN)。

BPS
复杂度分析
时间复杂度：O(N)O(N)，其中 NN 是树的节点数。对每个节点访问一次。
空间复杂度：O(N)O(N)，其中 NN 是树的节点数。空间复杂度主要取决于队列的开销，队列中的元素个数不会超过树的节点数。

class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        Queue<TreeNode> queNode = new LinkedList<TreeNode>();
        Queue<Integer> queVal = new LinkedList<Integer>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode now = queNode.poll();
            int temp = queVal.poll();
            if (now.left == null && now.right == null) {
                if (temp == sum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queNode.offer(now.left);
                queVal.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queNode.offer(now.right);
                queVal.offer(now.right.val + temp);
            }
        }
        return false;
    }
}
