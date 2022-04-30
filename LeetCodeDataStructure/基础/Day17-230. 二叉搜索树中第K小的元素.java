## 之前还写错了 少写了递归内的 return ---> 注意写有返回值的递归函数时，需要加上return，不然，内部递归的return值返回不到外层。
## 打个比方如下：
int func(int i){
    if(i == 2){return 2;}
    i++;
    func(i);//应该写return func(i);
    return i + 1;
}
sudo func(1);
得到的是： 3 而不是 2

并且我这样写不行，因为会判定我:
Line 30: error: missing return statement

class Solution {
    public int i = 1;
    public int kthSmallest(TreeNode root, int k) {
        //因为是二叉搜索树 -- 所以最基本的办法是 中序遍历 左根右
        if(root.left != null){
            return kthSmallest(root.left, k);
        }
        if(i == k){
            return root.val;
        }
        i++;
        if(root.right != null){
            return kthSmallest(root.right, k);
        }
    }
}

## 第二版 加一个helper就很好通过了 并且100 % （此外，也可以用迭代法遍历并在 k 位置处停止）

执行结果：
通过
显示详情
添加备注

执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
41.4 MB
, 在所有 Java 提交中击败了
30.04%
的用户
通过测试用例：
93 / 93

class Solution {
    public int res = 0;
    public int i = 1;
    public int kthSmallest(TreeNode root, int k) {
        helper(root, k);
        return res;
    }
    public void helper(TreeNode root, int k){
        if(i == k + 1){
            return;
        }
        if(root.left != null){
            helper(root.left, k);
        }
        if(i == k){
            res = root.val;
        }
        i++;
        if(root.right != null){
            helper(root.right, k);
        }
    }
}

## 题解 拓展了 很复杂
"https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/solution/er-cha-sou-suo-shu-zhong-di-kxiao-de-yua-8o07/
