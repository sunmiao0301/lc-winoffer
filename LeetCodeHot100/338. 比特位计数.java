第一版
执行结果：
通过
执行用时：
2 ms
, 在所有 Java 提交中击败了
44.88%
的用户
内存消耗：
42.6 MB
, 在所有 Java 提交中击败了
22.99%
的用户
通过测试用例：
15 / 15
/*
0
1

1
2

1
2
6 --> 110 --> 2
7 --> 111 --> 3

8 --> 1000 --> 1
9 --> 1001 --> 2
10 --> 1010 --> 2
11 --> 1011 --> 3
12 --> 1100 --> 2

I found that
2 = 2 * 1
4 = 2 * 2
8 = 2 * 4
16 = 2 * 8

所以其实就是：
每次达到一个2的幂次
就重新开始对之前的所有数
加个1放入数组
直到出现下一次2的幂次
*/
class Solution {
    public int[] countBits(int n) {
        int[] ret = new int[n + 1];
        ret[0] = 0;
        if(n >= 1)
            ret[1] = 1;
        for(int i = 1, j = 1; i <= n; i++){
            if(i == j * 2){
                j = j * 2;
                ret[j] = 1;
            }
            else{
                ret[i] = ret[i - j] + 1;
            }
        }
        return ret;
    }
}

####
方法一：\text{Brian Kernighan}Brian Kernighan 算法
最直观的做法是对从 00 到 nn 的每个整数直接计算「一比特数」。每个 \texttt{int}int 型的数都可以用 3232 位二进制数表示，只要遍历其二进制表示的每一位即可得到 11 的数目。
利用 \text{Brian Kernighan}Brian Kernighan 算法，可以在一定程度上进一步提升计算速度。\text{Brian Kernighan}Brian Kernighan 算法的原理是：

对于任意整数 xx，令 x=x~\&~(x-1)x=x & (x−1)，该运算将 xx 的二进制表示的最后一个 11 变成 00。因此，对 xx 重复该操作，直到 xx 变成 00，则操作次数即为 xx 的「一比特数」。
对于给定的 nn，计算从 00 到 nn 的每个整数的「一比特数」的时间都不会超过 O(\log n)O(logn)，因此总时间复杂度为 O(n \log n)O(nlogn)。

class Solution {
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            bits[i] = countOnes(i);
        }
        return bits;
    }

    public int countOnes(int x) {
        int ones = 0;
        while (x > 0) {
            x &= (x - 1);
            ones++;
        }
        return ones;
    }
}

####
方法二：动态规划——最高有效位
方法一需要对每个整数使用 O(\log n)O(logn) 的时间计算「一比特数」。
可以换一个思路，当计算 ii 的「一比特数」时，如果存在 0 \le j<i0≤j<i，jj 的「一比特数」已知，且 ii 和 jj 相比，ii 的二进制表示只多了一个 11，则可以快速得到 ii 的「一比特数」。

class Solution {
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        int highBit = 0;
        for (int i = 1; i <= n; i++) {
            if ((i & (i - 1)) == 0) {
                highBit = i;
            }
            bits[i] = bits[i - highBit] + 1;
        }
        return bits;
    }
}

#### 
####
####
方法四：动态规划——最低设置位
class Solution {
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }
}

p.s.
利用 Brian Kernighan 算法，可以在一定程度上进一步提升计算速度。
Brian Kernighan 算法的原理是：对于任意整数 x，令 x = x & (x-1)
该运算将 x 的二进制表示的最后一个 1 变成 0。 -------------------------- 注意 是最后一个1 不是最后一位一定是1
因此，对 x 重复该操作，直到 x 变成 0，则操作次数即为 x 的「一比特数」。

p.s.
因此对任意正整数 xx，都有 bits[x]=bits[x&(x-1)]+1
