## 二刷 一遍过 但是效率一般
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(stack.isEmpty()){
                stack.push(s.charAt(i));
            }
            else if(s.charAt(i) == ')' && stack.peek() == '('){
                stack.pop();
            }
            else if(s.charAt(i) == '}' && stack.peek() == '{'){
                stack.pop();
            }
            else if(s.charAt(i) == ']' && stack.peek() == '['){
                stack.pop();
            }            
            else{
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }
}

## 二刷 题解
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

//第一版 一遍过 但是效率一般 唯一需要注意的是 交 并 之间的括号需要注意 也就是：
((s.charAt(i) == ')' && stack.peek() == '(') || (s.charAt(i) == '}' && stack.peek() == '{') || (s.charAt(i) == ']' && stack.peek() == '['))
需要一个大括号括住，不然后面的 || 与前面的(!stack.isEmpty())就构成了并 即使第一个不满足 后面的满足 依然执行 这样与需要的逻辑不符

执行结果：
通过
执行用时：
2 ms
, 在所有 Java 提交中击败了
68.17%
的用户
内存消耗：
36.7 MB
, 在所有 Java 提交中击败了
23.46%
的用户 

//这一题与我之前笔试时遇到的一道题很相像，那个题是箱子装砖头的问题好像是，待会找找看

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for(int i = 0; i < len; i++){
            //1 <= s.length <= 104
            if(!stack.isEmpty()
            && ((s.charAt(i) == ')' && stack.peek() == '(')
            || (s.charAt(i) == '}' && stack.peek() == '{')
            || (s.charAt(i) == ']' && stack.peek() == '[')
            ))
            {
                stack.pop();
            }
            else  
                stack.push(s.charAt(i));
        }
        return stack.isEmpty();
    }
}

//第二版 路人版 很牛 这个思路的精髓在于 “预判” 并且化 “字符间的对应” 为 “本质上的对应” 很巧妙
一旦有[ 就立刻在stack放入] 因为必然是这样的，只有]之前的所有对称都结束了，才能轮到验证]与[
也就是吧 [()] 化成等价的[]()
这样就不需要根据s.charAt(i)来判断 而直接stack.pop()看是否相等即可。

执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
99.18%
的用户
内存消耗：
36.7 MB
, 在所有 Java 提交中击败了
19.19%
的用户
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        int len = s.length();
        for(int i = 0; i < len; i++){
            if(s.charAt(i) == '[')
                stack.push(']');
            else if(s.charAt(i) == '{')
                stack.push('}');
            else if(s.charAt(i) == '(')
                stack.push(')');
            else if(stack.isEmpty() || s.charAt(i) != stack.pop())//·······················需要注意的是 这里即使是判断一下 也pop执行了
                return false;
        }
        return stack.isEmpty();
    }
}

//第三版 K神 代码可复用性强 但是空间需要（hashmap）
class Solution {
    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')'); put('?','?');
    }};
    public boolean isValid(String s) {
        if(s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>() {{ add('?'); }};
        for(Character c : s.toCharArray()){
            if(map.containsKey(c)) stack.addLast(c);
            else if(map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }
}
