## 第一版 思路一般
## 用了两个栈来实现，思路是：
## 一个栈用于存储括号
## 一个栈用于存储括号对应的 数组中索引
## 然后括号栈中按照 括号的匹配情况 进行出入栈（对应的括号数组索引栈也进行对应的出入栈）
## 最后根据数组索引栈，对字符串进行删除即可。

执行结果：
通过
显示详情
添加备注

执行用时：
55 ms
, 在所有 Java 提交中击败了
11.11%
的用户
内存消耗：
41.8 MB
, 在所有 Java 提交中击败了
69.71%
的用户
通过测试用例：
62 / 62

class Solution {
    public String minRemoveToMakeValid(String s) {
        //删除最少数目的 '(' 或者 ')' 
        //本质上就是满足()对称即可
        //1 <= s.length <= 105
        Stack<Character> stack = new Stack<>();
        Stack<Integer> stackIndex = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char tmp = s.charAt(i);
            sb.append(tmp);

            if(tmp == '('){
                stack.push('(');
                stackIndex.push(i);
            }
            else if(s.charAt(i) == ')'){
                if(stack.isEmpty() || stack.peek() == ')'){
                    stack.push(')');
                    stackIndex.push(i);
                }
                else{
                    stack.pop();
                    stackIndex.pop();
                }
            }
        }
        while(!stackIndex.isEmpty()){
            int index = stackIndex.pop();
            sb.delete(index, index + 1);
        }
        return sb.toString();
        //sb.delete(stackIndex.pop(), 1);
    }
}
