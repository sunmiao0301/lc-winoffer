## 二刷 原本准备写成这样 后来发现想复杂了
class Solution {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(stack.isEmpty()){
                stack.push(s.charAt(i));
            }
            else if(s.charAt(i) == stack.peek()){
                stack.pop();
                while(!stack.isEmpty()){
                    char tmp = stack.pop();
                    if(stack.isEmpty()){
                        stack.push(tmp);
                        break;
                    }
                    else if(stack.peek() == tmp){
                        stack.pop();
                    }
                    else{
                        stack.push(tmp);
                        break;
                    }
                }
            }
            else{
                stack.push(s.charAt(i));
            }
        }
    }
}
 
## 二刷第二版 一遍过 但是效率很低
执行结果：
通过
显示详情
添加备注

执行用时：
68 ms
, 在所有 Java 提交中击败了
44.73%
的用户
内存消耗：
41.8 MB
, 在所有 Java 提交中击败了
54.43%
的用户
通过测试用例：
106 / 106
class Solution {
    public String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(stack.isEmpty()){
                stack.push(s.charAt(i));
            }
            else if(s.charAt(i) == stack.peek()){
                stack.pop();
            }
            else{
                stack.push(s.charAt(i));
            }
        }
        char[] arr = new char[stack.size()];
        for(int i = arr.length - 1; i >= 0; i--){
            arr[i] = stack.pop();
        }
        return new String(arr);
    }
}

## 二刷 题解 StringBuilder 不一定非要用栈 而是用到栈的思想
class Solution {
    public String removeDuplicates(String S) {
        char[] s = S.toCharArray();
        int top = -1;
        for (int i = 0; i < S.length(); i++) {
            if (top == -1 || s[top] != s[i]) {
                s[++top] = s[i];
            } else {
                top--;
            }
        }
        return String.valueOf(s, 0, top + 1);
    }
}

//第一版 一遍过 用的是双端队列 但是效率一般
执行结果：
通过
执行用时：
166 ms
, 在所有 Java 提交中击败了
11.37%
的用户
内存消耗：
44.1 MB
, 在所有 Java 提交中击败了
5.01%
的用户
class Solution {
    public String removeDuplicates(String s) {
    /*
    小写字母
    重复项删除操作会选择两个相邻且相同的字母
    其实如果不是在栈和队列专题中思考 是比较难想到要用这些数据结构的 我感觉
    */
    //Stack<Character> stack = new Stack<>();
    Deque<Character> deque = new LinkedList<>();
    int len = s.length();
    for(int i = 0; i < len; i++){
        if(!deque.isEmpty() && s.charAt(i) == deque.peekLast())
            deque.pollLast();
        else
            deque.addLast(s.charAt(i));
    }
    s = "";
    while(!deque.isEmpty()){
        s = s + deque.pollFirst();
    }
    return s;
    }
}

//第二版 后来想到不必用双端队列 栈即可 但是效率的提升不大
执行结果：
通过
执行用时：
163 ms
, 在所有 Java 提交中击败了
11.99%
的用户
内存消耗：
40.5 MB
, 在所有 Java 提交中击败了
11.64%
的用户
class Solution {
    public String removeDuplicates(String s) {
    Stack<Character> stack = new Stack<>();
    //Deque<Character> deque = new LinkedList<>();
    int len = s.length();
    for(int i = 0; i < len; i++){
        if(!stack.isEmpty() && s.charAt(i) == stack.peek())
            stack.pop();
        else
            stack.push(s.charAt(i));
    }
    s = "";
    while(!stack.isEmpty()){
        s = stack.pop() + s;
    }
    return s;
    }
}

//第三版 拿字符串直接作为栈，省去了栈还要转为字符串的操作。时间上要好不少
执行结果：
通过
执行用时：
12 ms
, 在所有 Java 提交中击败了
81.32%
的用户
内存消耗：
39.1 MB
, 在所有 Java 提交中击败了
42.62%
的用户
class Solution {
    public String removeDuplicates(String s) {
        StringBuffer stack = new StringBuffer();
        int top = -1;
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (top >= 0 && stack.charAt(top) == ch) {
                stack.deleteCharAt(top);
                --top;
            } else {
                stack.append(ch);
                ++top;
            }
        }
        return stack.toString();
    }
}

//第四版 看不懂 纯数组实现
class Solution {
    public String removeDuplicates(String s) {
        char[] cs = s.toCharArray();
        char[] d = new char[s.length()];
        int hh = 0, tt = -1;
        for (char c : cs) {
            if (hh <= tt && d[tt] == c) {
                tt--;
            } else {
                d[++tt] = c;
            }
        }  
        return new String(d, 0, tt + 1);
    }
} 
