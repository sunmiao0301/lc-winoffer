## 第一版 笨住了 把乘法都没想清楚。。。 以为乘法和加法一样了

class Solution {
    public String multiply(String num1, String num2) {
        //最后String.reverse();
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int a = 0;
        int b = 0;
        int i = 0;
        int len = Math.max(num1.length(), num2.length());
        while(i < len || carry != 0){
            if(i < num1.length()){
                a = num1.charAt(i) - '0';
            }
            if(i < num2.length()){
                b = num2.charAt(i) - '0';
            }
            int sum = a * b + carry;
            carry = 0;
            a = 0;
            b = 0;
            carry = sum / 10;
            sum = sum % 10;
            sb.append(sum);

            i++;
        }
        return sb.reverse().toString();
        //return (sb.toString().reverse());
    }
}

## 第二版
## 经过修改如下 通过 但是效率一般 
## 我的思路如下：首先将 num1 的每一位数和 num2 的每一位数相乘（根据 num1 的位数，在乘得到的结果后面加上对应个数的 0）
## 将得到的结果再全部相加。

执行结果：
通过
显示详情
添加备注

执行用时：
14 ms
, 在所有 Java 提交中击败了
36.14%
的用户
内存消耗：
41.6 MB
, 在所有 Java 提交中击败了
34.71%
的用户
通过测试用例：
311 / 311

class Solution {
    public String multiply(String num1, String num2) {
        //转化为先个位数乘多位数
        //然后乘积存在List<String>中在挨个相加
        if(num1.equals("0") || num2.equals("0")){
            return "0";
        }

        List<String> addNum = new ArrayList<>();
        for(int i = 0; i < num1.length(); i++){
            int c = num1.charAt(num1.length() - 1 - i) - '0';
            addNum.add(multiplyMinus(i, c, num2));
        }
        // return addNum.get(1);
        String res = "0";
        for(int i = 0; i < addNum.size(); i++){
            res = add(res, addNum.get(i));
        }
        return res;
    }
    public String multiplyMinus(int i, int c, String num2){
        StringBuilder sb = new StringBuilder();
        int j = num2.length() - 1;
        int carry = 0;
        while(j >= 0 || carry > 0){
            int tmp = 0;
            if(j >= 0){
                tmp = c * (num2.charAt(j) - '0') + carry;
            }
            else{
                tmp = carry;
            }
            carry = 0;
            carry = tmp / 10;
            sb.append(tmp % 10);
            j--;
        }
        sb.reverse();
        while(i > 0){
            sb.append("0");
            i--;
        }
        return sb.toString();
    }
    public String add(String strA, String strB){
        int a = strA.length() - 1;
        int b = strB.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while(a >= 0 || b >= 0 || carry > 0){
            int addA = 0, addB = 0;
            if(a >= 0){
                addA = strA.charAt(a) - '0';
                a--;
            }
            if(b >= 0){
                addB = strB.charAt(b) - '0';
                b--;
            }
            int tmp = addA + addB + carry;
            carry = 0;
            carry = tmp / 10;
            sb.append(tmp % 10);
        }
        return sb.reverse().toString();
    }
}

## 题解
## 我写的方法要分别进行 字符串乘法 和 字符串加法操作的
## 但是题解提出了一种只需要进行 字符串乘法操作 加法操作放到数组中进行的写法 如下：

方法二：做乘法
方法一的做法是从右往左遍历乘数，将乘数的每一位与被乘数相乘得到对应的结果，再将每次得到的结果累加，整个过程中涉及到较多字符串相加的操作。如果使用数组代替字符串存储结果，则可以减少对字符串的操作。

"https://leetcode-cn.com/problems/multiply-strings/solution/zi-fu-chuan-xiang-cheng-by-leetcode-solution/
