p.s. 三版之后的总结
1）注意比较的逻辑
2）注意，如果是用int来写两个值的比较函数，会出现溢出，需要换成long
3）当都是0的时候，一定是0在最开头，那么此时直接返回"0"即可

## 第一版 但是没考虑到数很大的时候的问题 于是有下面的错误样例

通过测试用例：
220 / 230
输入：
[999999998,999999997,999999999]
输出：
"999999998999999997999999999"
预期结果：
"999999999999999998999999997"

class Solution {
    public String largestNumber(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                if(aIsBiggerThanB(nums[j], nums[i])){
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums.length; i++){
            sb.append(nums[i]);
        }
        return sb.toString();
    }
    public boolean aIsBiggerThanB(int a, int b){//34 3 -- true
        int bitOfAMutiple = 1;
        int tmpA = a;
        while(tmpA != 0){
            tmpA = tmpA / 10;
            bitOfAMutiple *= 10;
        }
        int bitOfBMutiple = 1;
        int tmpB = b;
        while(tmpB != 0){
            tmpB = tmpB / 10;
            bitOfBMutiple *= 10;
        }
        int aFront = a * bitOfBMutiple + b;
        int bFront = b * bitOfAMutiple + a;
        if(aFront >= bFront){
            return true;
        }
        return false;
    }
}

## 第二版 但是没解决0的比较问题 于是倒在了下面的样例中

执行结果：
解答错误
显示详情
添加备注

通过测试用例：
221 / 230
输入：
[1,2,3,4,5,6,7,8,9,0]
输出：
"0987654321"
预期结果：
"9876543210"

class Solution {
    public String largestNumber(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                if(aIsBiggerThanB(nums[j], nums[i])){
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums.length; i++){
            sb.append(nums[i]);
        }
        return sb.toString();
    }
    public boolean aIsBiggerThanB(int a, int b){//34 3 -- true 1 0 -- true
        long bitOfAMutiple = 1;
        int tmpA = a;
        while(tmpA != 0){
            tmpA = tmpA / 10;
            bitOfAMutiple *= 10;
        }
        long bitOfBMutiple = 1;
        int tmpB = b;
        while(tmpB != 0){
            tmpB = tmpB / 10;
            bitOfBMutiple *= 10;
        }
        long aFront = a * bitOfBMutiple + b;
        long bFront = b * bitOfAMutiple + a;
        if(aFront >= bFront){
            return true;
        }
        return false;
    }
}

## 第三版 又倒在了新样例下 最后改一遍！

执行结果：
解答错误
显示详情
添加备注

通过测试用例：
226 / 230
输入：
[0,0]
输出：
"00"
预期结果：
"0"

class Solution {
    public String largestNumber(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                if(aIsBiggerThanB(nums[j], nums[i])){
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums.length; i++){
            sb.append(nums[i]);
        }
        return sb.toString();
    }
    public boolean aIsBiggerThanB(int a, int b){//34 3 -- true 1 0 -- true
        if(a == 0) return false;
        long bitOfAMutiple = 1;
        int tmpA = a;
        while(tmpA != 0){
            tmpA = tmpA / 10;
            bitOfAMutiple *= 10;
        }
        long bitOfBMutiple = 1;
        int tmpB = b;
        while(tmpB != 0){
            tmpB = tmpB / 10;
            bitOfBMutiple *= 10;
        }
        long aFront = a * bitOfBMutiple + b;
        long bFront = b * bitOfAMutiple + a;
        if(aFront >= bFront){
            return true;
        }
        return false;
    }
}

## 最后一版 通过 加了如下代码

        if (nums[0] == 0) {
            return "0";
        }
        
执行结果：
通过
显示详情
添加备注

执行用时：
2 ms
, 在所有 Java 提交中击败了
99.27%
的用户
内存消耗：
39.2 MB
, 在所有 Java 提交中击败了
82.27%
的用户
通过测试用例：
230 / 230

class Solution {
    public String largestNumber(int[] nums) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i; j < nums.length; j++){
                if(aIsBiggerThanB(nums[j], nums[i])){
                    int tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                }
            }
        }

        if (nums[0] == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < nums.length; i++){
            sb.append(nums[i]);
        }
        return sb.toString();
    }
    public boolean aIsBiggerThanB(int a, int b){//34 3 -- true 1 0 -- true
        if(a == 0) return false;
        long bitOfAMutiple = 1;
        int tmpA = a;
        while(tmpA != 0){
            tmpA = tmpA / 10;
            bitOfAMutiple *= 10;
        }
        long bitOfBMutiple = 1;
        int tmpB = b;
        while(tmpB != 0){
            tmpB = tmpB / 10;
            bitOfBMutiple *= 10;
        }
        long aFront = a * bitOfBMutiple + b;
        long bFront = b * bitOfAMutiple + a;
        if(aFront >= bFront){
            return true;
        }
        return false;
    }
}

## 题解

class Solution {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] ss = new String[n];
        for (int i = 0; i < n; i++) ss[i] = "" + nums[i];
        Arrays.sort(ss, (a, b) -> {
            String sa = a + b, sb = b + a ;
            return sb.compareTo(sa);
        });
        
        StringBuilder sb = new StringBuilder();
        for (String s : ss) sb.append(s);
        int len = sb.length();
        int k = 0;
        while (k < len - 1 && sb.charAt(k) == '0') k++;
        return sb.substring(k);
    }
}
