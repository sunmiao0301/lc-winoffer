## 第一版 但是还能优化
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

## 题解
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
