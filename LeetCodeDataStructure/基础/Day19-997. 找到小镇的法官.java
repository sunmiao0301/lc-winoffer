## 第一版 题目有点看不懂。。。

执行结果：
解答错误
显示详情
添加备注

通过测试用例：
4 / 92
输入：
4
[[1,3],[1,4],[2,3],[2,4],[4,3]]
输出：
-1
预期结果：
3

class Solution {
    public int findJudge(int n, int[][] trust) {
        //有 n 个人，按从 1 到 n 的顺序编号
        if(trust.length >= n){
            return -1;
        }
        int res = trust[0][1];//法官编号
        for(int i = 1; i < trust.length; i++){
            if(trust[i][1] != res){
                return -1;
            }
        }
        return res;
    }
}

## 第二版 理解了题意就很简单的完成了，题意为：
1）小镇上有很多人，人们直接可以互相信任，一个人可以同时信任0，1，2，....个人。
2）有一个法官，人们都信任他，同时法官信任的人为0，包括他自己。
3）如果第二条的法官不存在，或者能找到不止一个满足法官条件的人（即被其他所有人信任且同时不信任任何人），则都返回 -1。

执行结果：
通过
显示详情
添加备注

执行用时：
2 ms
, 在所有 Java 提交中击败了
97.91%
的用户
内存消耗：
49.1 MB
, 在所有 Java 提交中击败了
15.51%
的用户
通过测试用例：
92 / 92

class Solution {
    public int findJudge(int n, int[][] trust) {
        //第一版之所以做错了 是因为默认一个人只能信任一个人，实际上一个人可以信任多个人
        //此外 小镇法官不能信任任何人 包括自己 否则-1
        //trust[i].length == 2
        int[] getTrust = new int[n];
        int[] trustOther = new int[n];
        for(int i = 0; i < trust.length; i++){
            //因为编号人从 1 到 n 的顺序编号
            getTrust[trust[i][1] - 1]++;
            trustOther[trust[i][0] - 1]++;
        }
        //只有一个人同时满足属性 1 和属性 2 。(不能有多个人都满足法官的条件)
        int res = -1;
        for(int j = 0; j < n; j++){
            if(getTrust[j] == n - 1 && trustOther[j] == 0 && res != -1){
                return -1;
            }
            if(getTrust[j] == n - 1 && trustOther[j] == 0){
                res = j;
            }
        }
        if(res == -1){
            return -1;
        }
        return res + 1;
    }
}

## 题解 思路与我一致：
class Solution {
    public int findJudge(int n, int[][] trust) {
        int[] inDegrees = new int[n + 1];
        int[] outDegrees = new int[n + 1];
        for (int[] edge : trust) {
            int x = edge[0], y = edge[1];
            ++inDegrees[y];
            ++outDegrees[x];
        }
        for (int i = 1; i <= n; ++i) {
            if (inDegrees[i] == n - 1 && outDegrees[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
复杂度分析
时间复杂度：O(n+m)O(n+m)，其中 mm 是 \textit{trust}trust 的长度。首先需要遍历 \textit{trust}trust 计算出 \textit{inDegrees}inDegrees 和 \textit{outDegrees}outDegrees，然后需要遍历 \textit{inDegrees}inDegrees 和 \textit{outDegrees}outDegrees 来确定法官。
空间复杂度：O(n)O(n)。记录各个节点的入度和出度需要 O(n)O(n) 的空间。
