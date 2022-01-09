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
（这个问题只在使用 Java 语言或者其它有包装类型语法、自动装箱、拆箱机制的语言中，使用其它语言刷题的朋友可能不会遇到。）
我看来看去，确实也没有发现逻辑上的问题，于是我就把这段代码提交到 LeetCode 上，看看哪个测试用例没有过，打印输出了辅助栈 helper 的数据，就发现了问题所在。
简而言之，就是两个 peek() 方法返回的都是 Integer 类型 ，它们的比较不能用 ==，因为 == 用于包装类型（它们都是对象）的比较，比较的是它们的内存地址，解决方法也很简单：（1）改用 equals() 方法，（2）至少把其中一个变量转成 int 型。
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
        if(s1.peek().equals(s2.peek()))//问题在于将第一版中的==改为equals
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
