第一版 奇丑无比还错了
直接写第二版了 我记得有一种很优美的写法
class Solution {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        int len = s.length();
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == ']' && stack.pop() == '[')
                continue;
            if(s.charAt(i) == '}' && stack.pop() == '{')
                continue;
            if(s.charAt(i) == ')' && stack.pop() == '(')
                continue;
            if(s.charAt(i) == '[' || s.charAt(i) == '{' || s.charAt(i) == '(')
                stack.push(s.charAt(i));
            else
                return false;
        }
        if(stack.isEmpty())
            return true;
        return false;
    }
}

第二版 通过但是我感觉不优美 题目不难 但是想考虑到每种情况 对我来说还是要花点时间的
执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
98.84%
的用户
内存消耗：
36.5 MB
, 在所有 Java 提交中击败了
51.92%
的用户
通过测试用例：
91 / 91
class Solution {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        int len = s.length();
        for(int i = 0; i < len; i++){
            char temp = s.charAt(i);
            if(temp == '(' || temp == '[' || temp == '{')
                stack.push(temp);

            else if(temp == ')'){
                if(stack.isEmpty() || stack.pop() != '(')
                    return false;
            }
            else if(temp == ']'){
                if(stack.isEmpty() || stack.pop() != '[')
                    return false;
            }
            else if(temp == '}'){
                if(stack.isEmpty() || stack.pop() != '{')
                    return false;
            }
            else
                stack.pop();
        }
        if(stack.isEmpty())
            return true;
        return false;
    }
}

####
class Solution {
    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }
}
