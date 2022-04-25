## 第一版 之前一直报错让我百思不得其解，后面我瞎改了一下测试样例，结果通过了，我立马就想到是包装类的问题，改为 equals 果然通过

## 如果将 pop() 函数中的代码改为：

则测试结果如下：

(1)

错误
输入
["MinStack","push","push","push","push","pop","getMin","pop","getMin","pop","getMin"]
[[],[512],[-1024],[-1024],[512],[],[],[],[],[],[]]
输出
[null,null,null,null,null,null,-1024,null,-1024,null,-1024]
预期结果
[null,null,null,null,null,null,-1024,null,-1024,null,512]

(2)
正确
输入
["MinStack","push","push","push","push","pop","getMin","pop","getMin","pop","getMin"]
[[],[5],[-1],[-1],[5],[],[],[],[],[],[]]
输出
[null,null,null,null,null,null,-1,null,-1,null,5]
预期结果
[null,null,null,null,null,null,-1,null,-1,null,5]

因为 512与-1024 和 5与-1的关系在我看来完全没区别，所以我就想到 Integer 包装类的问题了，静态池

class MinStack {

    Stack<Integer> s1;
    Stack<Integer> s2;

    public MinStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    public void push(int val) {
        s1.push(val);
        if(s2.isEmpty() || val <= s2.peek()){
            s2.push(val);
        }
    }
    
    public void pop() {
        if(s1.peek().equals(s2.peek())){
            s2.pop();
        }
        s1.pop();
    }
    
    public int top() {
        return s1.peek();
    }
    
    public int getMin() {
        return s2.peek();
    }
}

## 题解 与我的思路略有不同

class MinStack {
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new LinkedList<Integer>();
        minStack = new LinkedList<Integer>();
        minStack.push(Integer.MAX_VALUE);
    }
    
    public void push(int x) {
        xStack.push(x);
        minStack.push(Math.min(minStack.peek(), x));
    }
    
    public void pop() {
        xStack.pop();
        minStack.pop();
    }
    
    public int top() {
        return xStack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
