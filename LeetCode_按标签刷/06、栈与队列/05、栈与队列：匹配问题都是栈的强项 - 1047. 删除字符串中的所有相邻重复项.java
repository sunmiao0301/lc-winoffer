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
