第一版
执行结果：
解答错误
通过测试用例：
24 / 31
输入：
["MinStack","push","push","push","push","pop","getMin","pop","getMin","pop","getMin"]
[[],[512],[-1024],[-1024],[512],[],[],[],[],[],[]]
输出：
[null,null,null,null,null,null,-1024,null,-1024,null,-1024]
预期结果：
[null,null,null,null,null,null,-1024,null,-1024,null,512]
class MinStack {
    Stack<Integer> s1;
    Stack<Integer> s2 ;

    public MinStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int val) {
        if(s2.isEmpty()){
            s2.push(val);
            s1.push(val);
        }
        else{
            if(val <= s2.peek())
                s2.push(val);
            s1.push(val);
        }
    }
    
    public void pop() {
        if(s1.peek() == s2.peek())
            s2.pop();
        s1.pop();
    }
    
    public int top() {
        return s1.peek();
    }
    
    public int getMin() {
        return s2.peek();
    }
}

第二版
执行结果：
通过
执行用时：
7 ms
, 在所有 Java 提交中击败了
13.87%
的用户
内存消耗：
40.1 MB
, 在所有 Java 提交中击败了
51.87%
的用户
通过测试用例：
31 / 31
修改 == 为 equals
class MinStack {
    Stack<Integer> s1;
    Stack<Integer> s2 ;

    public MinStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int val) {
        if(s2.isEmpty()){
            s2.push(val);
            s1.push(val);
        }
        else{
            if(val <= s2.peek())
                s2.push(val);
            s1.push(val);
        }
    }
    
    public void pop() {
        if(s1.peek().equals(s2.peek()))
            s2.pop();
        s1.pop();
    }
    
    public int top() {
        return s1.peek();
    }
    
    public int getMin() {
        return s2.peek();
    }
}
