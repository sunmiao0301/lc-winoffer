## 第一版 但是不对 我的写法 在更新后面的数组值的时候 破坏了前面的顺序。

执行结果：
解答错误
显示详情
添加备注

通过测试用例：
34 / 95
输入：
"DDI"
输出：
[1,2,0,3]
预期结果：
[3,2,0,1]

class Solution {
    public int[] diStringMatch(String s) {
        int n = s.length() + 1;
        int[] res = new int[n];
        res[0] = 0;
        for(int i = 1; i < n; i++){
            res[i] = i;
            if(s.charAt(i - 1) == 'D'){
                //swap
                int tmp = res[i];
                res[i] = res[i - 1];
                res[i - 1] = tmp;
            }
        }
        return res;
    }
}

## 第二版 灵光一现 写出来了
## 思路是找到所有连续的字符D（处于区间[a,b]），然后对于已经生成的数组[0, 1, 2, 3, 4...]，将其中处于[a, b + 1]区间的数字在数组中倒置即可。

执行结果：
通过
显示详情
添加备注

执行用时：
2 ms
, 在所有 Java 提交中击败了
87.44%
的用户
内存消耗：
41.7 MB
, 在所有 Java 提交中击败了
78.87%
的用户
通过测试用例：
95 / 95

class Solution {
    public int[] diStringMatch(String s) {
        //先构造数组 然后有 “连续D” 的地方就reverse
        int n = s.length() + 1;
        int[] res = new int[n];
        for(int i = 0; i < n; i++){
            res[i] = i;
        }
        int i = 0;
        while(i < n){
            int start = i;
            while(i < s.length() && s.charAt(i) == 'D'){
                i++;
            }
            reverse(start, i, res);
            i++;
        }
        return res;
    }
    public void reverse(int start, int end, int[] res){
        while(start < end){
            int tmp = res[start];
            res[start] = res[end];
            res[end] = tmp;
            start++;
            end--;
        }
    }
}

## 题解 贪心
考虑 perm[0] 的值，根据题意：
如果 s[0]=‘I’，那么令 perm[0]=0，则无论 perm[1] 为何值都满足 perm[0] < perm[1]；
如果 s[0]=‘D’，那么令 perm[0]=n，则无论 perm[1] 为何值都满足 perm[0] > perm[1]；

确定好 perm[0] 后，剩余的 n−1 个字符和 n 个待确定的数就变成了一个和原问题相同，但规模为 n−1 的问题。
因此我们可以继续按照上述方法确定 perm[1]：
如果 s[1]=‘I’，那么令 perm[1] 为剩余数字中的最小数；
如果 s[1]=‘D’，那么令 perm[1] 为剩余数字中的最大数。
如此循环直至剩下一个数，填入 perm[n] 中。
代码实现时，由于每次都选择的是最小数和最大数，我们可以用两个变量 lo 和 hi 表示当前剩余数字中的最小数和最大数。

class Solution {
    public int[] diStringMatch(String s) {
        int n = s.length(), lo = 0, hi = n;
        int[] perm = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            perm[i] = s.charAt(i) == 'I' ? lo++ : hi--;
        }
        perm[n] = lo; // 最后剩下一个数，此时 lo == hi
        return perm;
    }
}
复杂度分析
时间复杂度：O(n)，其中 n 是字符串 s 的长度。
空间复杂度：O(1)，返回值不计入空间复杂度。
