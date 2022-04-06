## 二刷 一版过 但是实际上还不是最好的解法
class MyQueue {

    //均摊时间复杂度为 O(1) 的队列

    Stack<Integer> sPush;
    Stack<Integer> sPop;

    public MyQueue() {
        sPush = new Stack<Integer>();
        sPop = new Stack<Integer>();
    }
    
    public void push(int x) {
        while(!sPop.isEmpty()){
            sPush.push(sPop.pop());
        }
        sPush.push(x);
    }
    
    public int pop() {
        while(!sPush.isEmpty()){
            sPop.push(sPush.pop());
        }
        return sPop.pop();
    }
    
    public int peek() {
        while(!sPush.isEmpty()){
            sPop.push(sPush.pop());
        }
        return sPop.peek();
    }
    
    public boolean empty() {
        return (sPop.isEmpty() && sPush.isEmpty());
    }
}

## 二刷 标准题解
class MyQueue {

    Stack<Integer> stack1;
    Stack<Integer> stack2;

    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new Stack<>(); // 负责进栈
        stack2 = new Stack<>(); // 负责出栈
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {    
        dumpStack1();
        return stack2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        dumpStack1();
        return stack2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    // 如果stack2为空，那么将stack1中的元素全部放到stack2中
    private void dumpStack1(){
        if (stack2.isEmpty()){
            while (!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
    }
}


/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

//第一版 一遍过
但是这个方法并不符合题目进阶要求，具体见第二版
进阶：
你能否实现每个操作均摊时间复杂度为 O(1) 的队列？
换句话说，执行 n 个操作的总时间复杂度为 O(n) 
即使其中一个操作可能花费较长时间。、

此外
需要注意的是peek()
peek()可以理解为对于当前数据结构，下一个要pop()的值，但是不pop()就看一眼
于是
对于stack peek的就是栈顶元素
对于queue peek的就是队列头元素
执行结果：
通过
执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
36.3 MB
, 在所有 Java 提交中击败了
40.91%
的用户
class MyQueue {
    Stack<Integer> stackIn;
    Stack<Integer> stackOut;
    /** Initialize your data structure here. */
    public MyQueue() {
        stackIn = new Stack<>();
        stackOut = new Stack<>();
        //假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        if(stackOut.isEmpty())
            stackIn.push(x);
        else{
            while(!stackOut.isEmpty())
                stackIn.push(stackOut.pop());
            stackIn.push(x);
        }
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(stackIn.isEmpty())
            return stackOut.pop();
        else{
            while(!stackIn.isEmpty())
                stackOut.push(stackIn.pop());
            return stackOut.pop();
        }
    }
    
    /** Get the front element. */
    public int peek() {
        if(stackIn.isEmpty())
            return stackOut.peek();
        else{
            while(!stackIn.isEmpty())
                stackOut.push(stackIn.pop());
            return stackOut.peek();
        }
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
 
//第二版 满足均摊复杂度o(1)
 
一句话：只有在「输出栈」为空的时候，才发生一次性的「倒腾」

均摊 O(1) 解法
事实上，我们不需要在每次的「入栈」和「出栈」操作中都进行「倒腾」。
我们只需要保证，输入的元素总是跟在前面的输入元素的后面，而输出元素总是最早输入的那个元素即可。
可以通过调整「倒腾」的时机来确保满足上述要求，但又不需要发生在每一次操作中：

class MyQueue {
    Deque<Integer> out, in;
    public MyQueue() {
        in = new ArrayDeque<>();
        out = new ArrayDeque<>();
    }
    
    public void push(int x) {
        in.addLast(x);
    }
    
    public int pop() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) out.addLast(in.pollLast());
        }
        return out.pollLast();
    }
    
    public int peek() {
        if (out.isEmpty()) {
            while (!in.isEmpty()) out.addLast(in.pollLast());
        }
        return out.peekLast();
    }
    
    public boolean empty() {
        return out.isEmpty() && in.isEmpty();
    }
}
