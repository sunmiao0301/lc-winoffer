## 第一版 但是速度一般

执行结果：
通过
显示详情
添加备注

执行用时：
2 ms
, 在所有 Java 提交中击败了
40.57%
的用户
内存消耗：
41.5 MB
, 在所有 Java 提交中击败了
33.19%
的用户
通过测试用例：
317 / 317

class Solution {
    public String addStrings(String num1, String num2) {
        //insert -- append
        StringBuilder sb = new StringBuilder();
        int num1Index = num1.length() - 1;
        int num2Index = num2.length() - 1;
        int carry = 0;
        while(num1Index >= 0 || num2Index >= 0){
            //int sum = Integer.parseInt(num1.charAt(num1Index)) + Integer.parseInt(num2.charAt(num2Index)) + carry;
            int sum = carry;
            if(num1Index >=0 ){
                sum += (num1.charAt(num1Index) - '0');
            }
            if(num2Index >= 0){
                sum += (num2.charAt(num2Index) - '0');
            }
            //(num1.charAt(num1Index) - '0') + (num2.charAt(num2Index) - '0') + carry;
            if(sum >= 10){
                sb.insert(0, sum % 10);
                carry = 1;
            }
            else{
                sb.insert(0, sum);
                carry = 0;
            }
            if(num1Index >= 0)
                num1Index--;
            if(num2Index >= 0)
                num2Index--;
        }
        if(carry > 0){
            sb.insert(0, "1");
        }
        return sb.toString();
    }
}

## 题解 思路是一样的 但是题解用的是append 然后最后翻转

class Solution {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }
}
