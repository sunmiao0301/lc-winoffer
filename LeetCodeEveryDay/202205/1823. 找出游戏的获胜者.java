## 第一版 通过

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
38.4 MB
, 在所有 Java 提交中击败了
58.82%
的用户
通过测试用例：
95 / 95

class Solution {
    public int findTheWinner(int n, int m) {
        //0 1 2--> n = 3, m = 1 
        //1 2
        //2
        int pos = 0; 
        for(int i = 2; i < n + 1; i++){
            pos = (pos + m) % i;
        }
        return pos + 1;
    }
}

n = 5, m = 3
01234 --> 0134 --> 3401 || n = 5
3401 --> 341 --> 134 || n = 4
134 --> 13 --> 13 || n = 3
13 --> 3 --> 3 || n = 2
3 || n = 1

pos = 3, n = 5, m = 3
pos = 0, n = 4, m = 3
pos = 1, n = 3, m = 3
pos = 1, n = 2, m = 3 
pos = 0, n = 1, m = 3 

下面逆推
0 + 3 % 2 = 1
1 + 3 % 3 = 1
1 + 3 % 4 = 0
0 + 3 % 5 = 3
所以最后的值在第一轮的环的初始位置为3

再把这个反过来, pre表示上一层的位置，next表示下一层的对应位置
(next + m) % n_pre = pre
pre = (next + m) % n_pre

"注意，我们之所以可以这样推导，是因为我们不必管细节如何，我们只需知道，通过一个0，能反推出在 最终的返回值 在第一层的位置即可。

class Solution {
    public int findTheWinner(int n, int k) {
    //最后一层，最后剩下的的位置为0。
        int pos = 0;
        for (int i = 2; i < n + 1; ++i) {
            pos = (pos + k) % i;
        }
        return pos + 1;//加1是因为本题为0开始。
    }
}

题解链接 "https://leetcode-cn.com/problems/find-the-winner-of-the-circular-game/solution/by-fuxuemingzhu-laof/

此外，本题解也是标准题解中三种方法中最好的方法：
方法三：数学 + 迭代
方法二的递归实现可以改成迭代实现，省略递归调用栈空间。
复杂度分析
时间复杂度： O(n)，其中 n 是做游戏的小伙伴数量。需要 O(n) 的时间遍历并计算结果。
空间复杂度： O(1)。
