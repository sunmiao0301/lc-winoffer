2nd StringBuilder还是很好用的
class Solution {
    public String reverseWords(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == (' ')){
                if(sb.length() > 0)
                    stack.push(sb.toString());
                sb.delete(0, sb.length());
            }
            else
                sb.append(s.charAt(i));
        }
        if(sb.length() > 0)//防止sb = ""的时候放入一个""导致错误
            stack.push(sb.toString());
        sb.delete(0, sb.length());
        sb.append(stack.pop());
        while(!stack.isEmpty()){
            sb.append(" " + stack.pop());
        }
        return sb.toString();
    }
}

//第一遍 过了 但是写了蛮久 效率也不高 还是得学习一个好一点的标准思路 毕竟是常考题

执行结果：
通过
执行用时：
15 ms
, 在所有 Java 提交中击败了
6.64%
的用户
内存消耗：
39.1 MB
, 在所有 Java 提交中击败了
5.16%
的用户

(1)
class Solution {
    public String reverseWords(String s) {
    //s 包含英文大小写字母、数字和空格 ' '
    Stack<String> stack = new Stack<>();
    String ret = "";
    int i = 0, j = 0;
    for(; i < s.length(); i++){
        if(s.charAt(i) == ' '){
            stack.push(s.substring(j, i));
            j = i + 1;
        }
    }
    stack.push(s.substring(j, i));
    //ret += stack.pop();
    while(!stack.isEmpty()){
        if(stack.peek().equals(""))
            stack.pop();
        else
            ret += " " + stack.pop();//出错的原因是push了""进去
    }
    return ret.substring(1);
    }
}
(2)
class Solution {
    public String reverseWords(String s) {
    Stack<String> stack = new Stack<>();
    String temp = "";
    for(int i = 0; i < s.length(); i++){
        if(s.charAt(i) == ' '){
            stack.push(temp);
            temp = "";
        }
        else{
            temp += s.charAt(i);
        }
    }
    stack.push(temp);
    temp = "";
    while(!stack.isEmpty()){
        if(stack.peek().equals(""))
            stack.pop();
        else
            temp += " " + stack.pop();
    }
    return temp.substring(1);
    }
}

//第二版 标准题解

方法二：自行编写对应的函数

思路和算法

我们也可以不使用语言中的 API，而是自己编写对应的函数。在不同语言中，这些函数实现是不一样的，主要的差别是有些语言的字符串不可变（如 Java 和 Python)，有些语言的字符串可变（如 C++)。

对于字符串不可变的语言，首先得把字符串转化成其他可变的数据结构，同时还需要在转化的过程中去除空格。

对于字符串可变的语言，就不需要再额外开辟空间了，直接在字符串上原地实现。在这种情况下，反转字符和去除空格可以一起完成。


class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = trimSpaces(s);

        // 翻转字符串
        reverse(sb, 0, sb.length() - 1);

        // 翻转每个单词
        reverseEachWord(sb);

        return sb.toString();
    }

    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        // 将字符串间多余的空白字符去除
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }

            ++left;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') {
                ++end;
            }
            // 翻转单词
            reverse(sb, start, end - 1);
            // 更新start，去找下一个单词
            start = end + 1;
            ++end;
        }
    }
}
复杂度分析

时间复杂度：O(n)O(n)，其中 nn 为输入字符串的长度。

空间复杂度：Java 和 Python 的方法需要 O(n)O(n) 的空间来存储字符串，而 C++ 方法只需要 O(1)O(1) 的额外空间来存放若干变量。
