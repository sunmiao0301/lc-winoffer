## 自己没想出来

## 题解
## 需要了解 字典树
## 那么本题实际上就是字典 10 叉树的先序遍历 根左右
## 题解见："https://leetcode-cn.com/problems/lexicographical-numbers/solution/by-yusael-ddne/
## 具体内容如下：

手绘一副本题要用到的树：
0
|
-----------------------------------
|       |       |       | | | | | |
1       2       3       4 5 6 7 8 9
|       |       |       | | | | | |
10~19   20~29   30~39   ............
对于输入 20，得到的答案是：
[1,10,11,12,13,14,15,16,17,18,19,2,20,3,4,5,6,7,8,9]

题解的十叉树代码如下：
class Solution {
    List<Integer> list = new ArrayList<>();
    int n;

    public List<Integer> lexicalOrder(int n) {
        this.n = n;
        // 因为没有 0 ，相当于从 十叉树 的第 2 层开始遍历，也就是起始是 1 ~ 9
        for (int i = 1; i <= 9; i++){
            dfs(i);
        } 
        return list;
    }
    
    // 树的先序遍历 DFS
    void dfs(int k) {
        // 大于 n 就直接结束
        if (k > n) return;
        list.add(k);
        // 假如传来是 1, 继续遍历 10 ~ 19 加入 list
        for (int i = 0; i <= 9; i++){
            dfs(k * 10 + i);
        } 
    }
}

## 代码如下：
class Solution {
    List<Integer> ans = new ArrayList<>();
    public List<Integer> lexicalOrder(int n) {
        for (int i = 1; i <= 9; i++) dfs(i, n);// 因为没有 0 ，相当于从 十叉树 的第 2 层开始遍历，也就是起始是 1 ~ 9
        return ans;
    }
    void dfs(int cur, int limit) {// 树的先序遍历 DFS
        if (cur > limit) return ;/// 大于 n 就直接结束
        ans.add(cur);
        for (int i = 0; i <= 9; i++) dfs(cur * 10 + i, limit);// 假如传来是 1, 继续遍历 10 ~ 19 加入 list
    }
}

## 根据十叉树的思路 我写出来的如下：

执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
99.98%
的用户
内存消耗：
45.5 MB
, 在所有 Java 提交中击败了
67.57%
的用户
通过测试用例：
26 / 26

class Solution {
    public List<Integer> res = new ArrayList<>();
    public List<Integer> lexicalOrder(int n) {
        //while(res.size() < n){
        for(int i = 1; i < 10; i++){//十叉树的首层 -- 因为第一层从0开始 所以单独写个for(1 ~ 9)
            if(res.size() < n){
                res.add(i);
                dfs(i, n);
            }
            else{
                return res;
            }
        }
        //}
        return res;
    }
    public void dfs(int i, int n){
        for(int j = 0; j < 10; j++){
            int tmp = i * 10 + j;
            if(tmp <= n){
                res.add(tmp);
                dfs(tmp, n);
            }
            else{
                return;
            }
        }
    }
}

## 
递归具有额外的空间开销，为了实现严格的 O(1) 空间，我们需要使用「迭代」来实现 DFS。

共有 n 个数需要被处理，假设当前处理到的数为 j ，根据字典序规则，在满足条件的前提下，我们优先在 j 的后面添加 0（即 j * 10 < n 满足），否则我们考虑将上一位回退并进行加一操作。

代码：

class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, j = 1; i < n; i++) {
            ans.add(j);
            if (j * 10 <= n) {
                j *= 10;
            } else {
                while (j % 10 == 9 || j + 1 > n) j /= 10;// while 保证回退
                j++;
            }
        }
        return ans;
    }
}
时间复杂度： O(n)
空间复杂度： O(1)

