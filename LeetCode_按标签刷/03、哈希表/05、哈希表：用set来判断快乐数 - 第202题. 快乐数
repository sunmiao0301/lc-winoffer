//第一版 肯定会超时 因为是硬算 结果果然超时了 但是实际上超时的原因是出错了
class Solution {
    Set<Integer> set = new HashSet<>();
    public boolean isHappy(int n) {
    /*
    直到这个数变为 1，也可能是 无限循环 但始终变不到 1
    感觉不太简单 但是可以猜测思路set存 一旦有且没到1 就false(题目说是无限循环)
    */
    while(n != 1 && !set.contains(n)){
        int temp = 0;
        while(n != 0){
            temp += (n % 10) * (n % 10);
            n /= 10;
        }
        set.add(n);
        n = temp;
    }
    if(n == 1)return true;
        return false;
    }
}

//第二版 对第一版修正之后 通过

这一题的重点在于：

题目中说了会 无限循环，那么也就是说求和的过程中，sum会重复出现，这对解题很重要！
正如：关于哈希表，你该了解这些！中所说，当我们遇到了要快速判断一个元素是否出现集合里的时候，就要考虑哈希法了。
所以这道题目使用哈希法，来判断这个sum是否重复出现，如果重复了就是return false， 否则一直找到sum为1为止。
判断sum是否重复出现就可以使用unordered_set。


执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
98.20%
的用户
内存消耗：
35.6 MB
, 在所有 Java 提交中击败了
28.76%
的用户
class Solution {
    Set<Integer> set = new HashSet<>();
    int temp = 0;
    public boolean isHappy(int n) {
    /*
    直到这个数变为 1，也可能是 无限循环 但始终变不到 1
    感觉不太简单 但是可以猜测思路set存 一旦有且没到1 就false(题目说是无限循环)
    */
    while(n != 1 && !set.contains(n)){
        set.add(n);
        temp = 0;
        while(n != 0){
            temp += (n % 10) * (n % 10);
            n /= 10;
        }
        n = temp;
    }
    if(n == 1)return true;
        return false;
    }
}

//标准题解 思路与我一致
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)) {
            record.add(n);
            n = getNextNumber(n);
        }
        return n == 1;
    }

    private int getNextNumber(int n) {
        int res = 0;
        while (n > 0) {
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }
        return res;
    }
}
