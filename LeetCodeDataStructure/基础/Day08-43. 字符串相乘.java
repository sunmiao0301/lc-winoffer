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
