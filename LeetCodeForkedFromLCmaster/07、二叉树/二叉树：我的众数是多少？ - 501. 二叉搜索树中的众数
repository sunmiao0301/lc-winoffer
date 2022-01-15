//第一版 室友睡觉 明天继续
class Solution {
    public int[] findMode(TreeNode root) {
    /*
    有相同值 只能用递归
    不只是二叉树，而是有相同值的二叉搜索树
    我的思路是通过递归中序遍历 连续的话就通过参数记载 
    直到不连续就刷新众数 但是怎么不要额外空间来存储众数呢？
    好想用哈希表 但是不给加空间 但是怎么可能呢 必须得加 至少得加一个动态数组存一下吧
    */
    List<Integer> array = new ArrayList<>();
    int max_freq = 0, bef = 一个不等于搜索二叉树中最小值的值;
    dfs(root, 0);
    //return array;
    }
    void dfs(TreeNode root, int cur_freq){
        if(root.left != null){
            dfs(root.left, cur_freq);
        }
        if(root.val == bef)
            cur_freq++;
        else
            cur_freq = 1;
        if(cur_freq == max_freq){
            array.add(root.val);
        }
        else if(cur_freq > max_freq){
            max_freq = cur_freq;
            array.clear();
            array.add(root.val);
        }
        bef = root.val;
        if(root.right != null){
            dfs(root.right, cur_freq);
        }
    }
}

//第二版 康复训练后从这里开始 就只会暴力了
执行结果：
通过
执行用时：
3 ms
, 在所有 Java 提交中击败了
29.96%
的用户
内存消耗：
40.1 MB
, 在所有 Java 提交中击败了
10.85%
的用户
通过测试用例：
23 / 23
class Solution {
    public List<Integer> mode = new ArrayList<>();
    public Map<Integer, Integer> map = new HashMap<>();
    public int modeVal = 0;
    public int[] findMode(TreeNode root) {
        //int[] mode = new int[];
        helper(root);
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            if(entry.getValue() == modeVal)
                mode.add(entry.getKey());
        }
        int[] ret = new int[mode.size()];
        for(int i = 0; i < mode.size(); i++){
            ret[i] = mode.get(i);
        }
        return ret;
    }
    public void helper(TreeNode root){
        //if(root.left == null && root.right == null){
            if(map.containsKey(root.val)){
                int value = map.get(root.val);
                map.put(root.val, value + 1);
                if(value + 1 > modeVal)
                    modeVal = value + 1;
            }
            else{
                map.put(root.val, 1);
                if(1 > modeVal)
                    modeVal = 1;
            }
        //}
        if(root.left == null && root.right == null){}
        else if(root.left != null && root.right == null){
            helper(root.left);
        }
        else if(root.left == null && root.right != null){
            helper(root.right);
        }
        else{
            helper(root.left);
            helper(root.right);
        }
    } 
}

#### 看了题解 是Morris 中序遍历 暂时跳过
