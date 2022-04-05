## 判断质数
    public boolean isPrime(int x){
        if(x == 1)return false;//注意1不是质数
        for(int i = 2; i * i <= x; i++){//注意是小于等于
            if(x % i == 0)
                return false;
        }
        return true;
    }

## 计算二进制中1的个数
    public int sumOfOne(int x){
        int res = 0;
        while(x != 0){
            if((x & 1) == 1)
                res++;
            x = x >>> 1;
        }
        return res;
    }
    
    public int hammingWeight(int n) {
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }




## 综上两个函数 第一版如下 但是效率一般
执行结果：
通过
显示详情
添加备注

执行用时：
38 ms
, 在所有 Java 提交中击败了
22.35%
的用户
内存消耗：
38 MB
, 在所有 Java 提交中击败了
73.01%
的用户
通过测试用例：
202 / 202
class Solution {
    public int countPrimeSetBits(int left, int right) {
        int res = 0;
        for(int i = left; i <= right; i++){
            if(isPrime(sumOfOne(i))){
                res++;
            }
        }
        return res;
    }
    public int sumOfOne(int x){
        int res = 0;
        while(x != 0){
            if((x & 1) == 1)
                res++;
            x = x >>> 1;
        }
        return res;
    }
    public boolean isPrime(int x){
        if(x == 1)return false;
        for(int i = 2; i * i <= x; i++){
            if(x % i == 0)
                return false;
        }
        return true;
    }
}

## 将sumOfOne函数优化一下 效率高了很多 -- 位运算
执行结果：
通过
显示详情
添加备注

执行用时：
6 ms
, 在所有 Java 提交中击败了
48.89%
的用户
内存消耗：
38.2 MB
, 在所有 Java 提交中击败了
61.95%
的用户
通过测试用例：
202 / 202
    public int sumOfOne(int n){
        int ret = 0;
        while (n != 0) {
            n &= n - 1;
            ret++;
        }
        return ret;
    }

#### 更多的优化方法见题解
https://leetcode-cn.com/problems/prime-number-of-set-bits-in-binary-representation/solution/er-jin-zhi-biao-shi-zhong-zhi-shu-ge-ji-jy35g/
