## 第一版
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
37.7 MB
, 在所有 Java 提交中击败了
80.55%
的用户
通过测试用例：
55 / 55
class Solution {
    public int cuttingRope(int n) {
        //m>1
        if(n == 2){
            return 1;
        }
        else if(n == 3){
            return 2;
        }
        else if(n == 4){
            return 4;
        }

        //n > 4
        
        else{
            return (int)(3 * helper(n - 3) % 1000000007);
        }
    }
    public long helper(int n){
        if(n < 5){
            return n;
        }
        else{
            long tmp = 3 * helper(n - 3);
            tmp = tmp % 1000000007;
            return tmp;
            // return 3 * helper(n - 3) % 1000000007;
        }
    }
}
