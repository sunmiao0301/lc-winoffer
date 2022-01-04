第一版
执行结果：
解答错误
显示详情
添加备注

通过测试用例：
17 / 149
输入：
93
73
输出：
6
预期结果：
2

/*
  3 = 0 1 1
  1 = 0 0 1
SUM = 0 1 0 = 2
2 / 2 = 0
TIMES = 1
*/
class Solution {
    public int hammingDistance(int x, int y) {
        int sumXAndY = x | y;
        int numberOfDiffBits = 0;
        while(sumXAndY != 0){
            if(sumXAndY / 2 != 0){
                numberOfDiffBits++;
            }
            sumXAndY = sumXAndY >> 1;
        }
        return numberOfDiffBits;
    }
}

第二版
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
35.4 MB
, 在所有 Java 提交中击败了
9.31%
的用户
通过测试用例：
149 / 149
/*
  3 = 0 1 1
  1 = 0 0 1
dis = 0 1 0
目标应该是异或结果（不同为1 相同为0）
java中如何表示异或
p.s.异或可以理解为一种不进位加法
*/
class Solution {
    public int hammingDistance(int x, int y) {
        int sumXAndY = x ^ y;
        int numberOfDiffBits = 0;
        while(sumXAndY != 0){
            if(sumXAndY % 2 != 0){
                numberOfDiffBits++;
            }
            sumXAndY = sumXAndY >> 1;
        }
        return numberOfDiffBits;
    }
}


#### 
方法三：\text{Brian Kernighan}Brian Kernighan 算法
思路及算法

在方法二中，对于 s=(10001100)_2s=(10001100) 的情况，我们需要循环右移 88 次才能得到答案。而实际上如果我们可以跳过两个 11 之间的 00，直接对 11 进行计数，那么就只需要循环 33 次即可。

我们可以使用 Brian Kernighan 算法进行优化，具体地，该算法可以被描述为这样一个结论：
记 f(x)f(x) 表示 xx 和 x-1x−1 进行与运算所得的结果（即 f(x)=x~\&~(x-1)f(x)=x & (x−1)），那么 f(x)f(x) 恰为 xx 删去其二进制表示中最右侧的 11 的结果。
基于该算法，当我们计算出 s = x \oplus ys=x⊕y，只需要不断让 s = f(s)s=f(s)，直到 s=0s=0 即可。
这样每循环一次，ss 都会删去其二进制表示中最右侧的 11，最终循环的次数即为 ss 的二进制表示中 11 的数量。
class Solution {
    public int hammingDistance(int x, int y) {
        int s = x ^ y, ret = 0;
        while (s != 0) {
            s &= s - 1;
            ret++;
        }
        return ret;
    }
}
