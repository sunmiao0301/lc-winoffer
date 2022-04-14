## 第一版 最佳实现
执行结果：
通过
显示详情
添加备注

执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
38.9 MB
, 在所有 Java 提交中击败了
72.35%
的用户
通过测试用例：
22 / 22
class MyQueue {

    //均摊时间复杂度为 O(1) 的队列

    Stack<Integer> sPush;
    Stack<Integer> sPop;

    public MyQueue() {
        sPush = new Stack<Integer>();
        sPop = new Stack<Integer>();
    }
    
    public void push(int x) {
        sPush.push(x);
    }
    
    public int pop() {
        if(sPop.isEmpty()){
            while(!sPush.isEmpty()){
                sPop.push(sPush.pop());
            }
        }
        return sPop.pop();
    }
    
    public int peek() {
        if(sPop.isEmpty()){
            while(!sPush.isEmpty()){
                sPop.push(sPush.pop());
            }
        }
        return sPop.peek();
    }
    
    public boolean empty() {
        return (sPop.isEmpty() && sPush.isEmpty());
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
