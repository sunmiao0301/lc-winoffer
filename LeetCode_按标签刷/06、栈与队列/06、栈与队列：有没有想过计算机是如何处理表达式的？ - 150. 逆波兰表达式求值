//第一版 不对 没有对逆波兰表达式的逻辑进行简化
class Solution {
    public int evalRPN(String[] tokens) {
    Stack<String> stack = new Stack<>();
    int len = tokens.length, i = 1;
    stack.push(tokens[0]);
    while(i < len - 1){
        if(!helper(tokens[i]) && helper(tokens[i + 1]) && !helper(stack.peek())) {
            stack.push(helper(stack.pop(), tokens[i], tokens[i + 1]));
            i += 2;
        }
        else{
            stack.push(tokens[i]);
            i++;            
        }
    }
    return Integer.valueOf(stack.pop());
    }
    String helper(String num1, String num2, String opr){
        if(opr.equals("+"))
            return (Integer.valueOf(num1) + Integer.valueOf(num2)) + "";
        else if(opr.equals("-"))
            return (Integer.valueOf(num1) - Integer.valueOf(num2)) + "";
        else if(opr.equals("/"))
            return (Integer.valueOf(num1) / Integer.valueOf(num2)) + "";
        else
            return (Integer.valueOf(num1) * Integer.valueOf(num2)) + "";
    }
    boolean helper(String s){
        if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
            return true;
        return false;
    }
}

//第二版 通过 但是效率一般 为了优化 我发现一个办法 因为其实栈中是没有操作符的 所以直接<Integer>就行了
执行结果：
通过
执行用时：
12 ms
, 在所有 Java 提交中击败了
9.89%
的用户
内存消耗：
38.2 MB
, 在所有 Java 提交中击败了
29.79%
的用户
class Solution {
    public int evalRPN(String[] tokens) {
    /*
    适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
    */
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < tokens.length; i++){
            if(isOpr(tokens[i])){
                String num2 = stack.pop();
                String num1 = stack.pop();
                stack.push(exec(num1, num2, tokens[i]));
            }
            else
                stack.push(tokens[i]);
        }
        return Integer.valueOf(stack.pop());
    }
    boolean isOpr(String s){
        if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
            return true;
        return false;
    }
    String exec(String num1, String num2, String opr){
        if(opr.equals("+"))
            return (Integer.valueOf(num1) + Integer.valueOf(num2)) + "";
        else if(opr.equals("-"))
            return (Integer.valueOf(num1) - Integer.valueOf(num2)) + "";
        else if(opr.equals("*"))
            return (Integer.valueOf(num1) * Integer.valueOf(num2)) + "";
        else
            return (Integer.valueOf(num1) / Integer.valueOf(num2)) + "";
    }
}

//第三版 栈用的是Stack<Integer> 效果好多了
执行结果：
通过
执行用时：
5 ms
, 在所有 Java 提交中击败了
93.08%
的用户
内存消耗：
38.1 MB
, 在所有 Java 提交中击败了
53.37%
的用户
class Solution {
    public int evalRPN(String[] tokens) {
    /*
    适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
    */
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < tokens.length; i++){
            if(isOpr(tokens[i])){
                int num2 = stack.pop();
                int num1 = stack.pop();
                stack.push(exec(num1, num2, tokens[i]));
            }
            else
                stack.push(Integer.valueOf(tokens[i]));
        }
        return stack.pop();
    }
    boolean isOpr(String s){
        if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/"))
            return true;
        return false;
    }
    int exec(int num1, int num2, String opr){
        if(opr.equals("+"))
            return (Integer.valueOf(num1) + Integer.valueOf(num2));
        else if(opr.equals("-"))
            return (Integer.valueOf(num1) - Integer.valueOf(num2));
        else if(opr.equals("*"))
            return (Integer.valueOf(num1) * Integer.valueOf(num2));
        else
            return (Integer.valueOf(num1) / Integer.valueOf(num2));
    }
}

//第四版 不过最快的方法应该还是用数组模拟栈

