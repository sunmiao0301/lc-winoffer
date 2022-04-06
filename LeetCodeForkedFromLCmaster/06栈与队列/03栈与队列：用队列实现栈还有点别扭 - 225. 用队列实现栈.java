## 二刷 第一版 但是我写的是push比较简单，pop和top比较复杂，实际上可以写成push复杂，pop和top简单的形式。
class MyStack {

    //你能否仅用一个队列来实现栈。
    Queue<Integer> q;

    public MyStack() {
        q = new LinkedList<Integer>();
    }
    
    public void push(int x) {
        q.offer(x);
    }
    
    public int pop() {
        int size = q.size();
        for(int i = 0; i < size - 1; i++){
            q.offer(q.poll());
        }
        return q.poll();
    }
    
    public int top() {
        int size = q.size();
        for(int i = 0; i < size - 1; i++){
            q.offer(q.poll());
        }
        int res = q.peek();
        q.offer(q.poll());
        return res;
    }
    
    public boolean empty() {
        return q.isEmpty();
    }
}

## 二刷 题解 push复杂，pop和top简单的形式。
class MyStack {
    Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        int n = queue.size();
        queue.offer(x);
        for (int i = 0; i < n; i++) {
            queue.offer(queue.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}


//第一版 先做只用两个Queue完成的
    1)
    请你仅使用两个队列实现一个后入先出（LIFO）的栈，
    并支持普通栈的全部四种操作（push、top、pop 和 empty）。
    因为size()卡了一下 但是自己解决了

出错的原因是size()函数需要注意！
包括未来的String.length()都要注意 
防止被动体变化导致for循环中的size()值变化，
导致for循环的结果与预想的值不一致

class MyStack {
    Queue<Integer> queueIn;
    Queue<Integer> queueOut;
    /** Initialize your data structure here. */
    public MyStack() {
        queueIn = new LinkedList<>();
        queueOut = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        if(!queueOut.isEmpty())
            queueOut.offer(x);
        else
            queueIn.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if(queueIn.isEmpty()){
            int size = queueOut.size();//····························如果这里不先取出来size值，for循环的时候 queue.size()会随着循环而变小，进而和想要的效果不一致，出错
            for(int i = 0; i < size - 1; i++){
                queueIn.offer(queueOut.poll());
            }
            return queueOut.poll();
        }
        else{
            int size = queueIn.size();
            for(int i = 0; i < size - 1; i++){
                queueOut.offer(queueIn.poll());
            }
            return queueIn.poll();
        }
    }
    
    /** Get the top element. */
    public int top() {
        if(queueIn.isEmpty()){
            int size = queueOut.size();
            for(int i = 0; i < size - 1; i++){
                queueIn.offer(queueOut.poll());
            }
            int temp = queueOut.poll();
            queueIn.offer(temp);
            return temp;
        }
        else{
            int size = queueIn.size();
            for(int i = 0; i < size - 1; i++){
                queueOut.offer(queueIn.poll());
            }
            int temp = queueIn.poll();
            queueOut.offer(temp);
            return temp;
        }        
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queueIn.isEmpty() && queueOut.isEmpty();
    }
}

//第二版
    2)
    进阶：你能否实现每种操作的均摊时间复杂度为 O(1) 的栈？
    换句话说，执行 n 个操作的总时间复杂度 O(n) ，
    尽管其中某个操作可能需要比其他操作更长的时间。
    你可以使用两个以上的队列。
    
想半天没想出来，实际上是： 

方法一使用了两个队列实现栈的操作，也可以使用一个队列实现栈的操作。（如果用两个，其实与一个没有区别，queue2其实一直都属于空的状态，就是为了放进去一个新元素，然后把queue1的元素折腾进去，使得后放进去的元素在最前面，然后再交换queue1和queue2，这时候queue2就又为空了。）
使用一个队列时，为了满足栈的特性，即最后入栈的元素最先出栈，同样需要满足队列前端的元素是最后入栈的元素。
入栈操作时，首先获得入栈前的元素个数 nn，然后将元素入队到队列，再将队列中的前 nn 个元素（即除了新入栈的元素之外的全部元素）依次出队并入队到队列，此时队列的前端的元素即为新入栈的元素，且队列的前端和后端分别对应栈顶和栈底。
由于每次入栈操作都确保队列的前端元素为栈顶元素，因此出栈操作和获得栈顶元素操作都可以简单实现。出栈操作只需要移除队列的前端元素并返回即可，获得栈顶元素操作只需要获得队列的前端元素并返回即可（不移除元素）。
由于队列用于存储栈内的元素，判断栈是否为空时，只需要判断队列是否为空即可。

class MyStack {
    Queue<Integer> queue;
    public MyStack() {
        queue = new LinkedList<>();
    }
    
    public void push(int x) {//这里是最关键的，通过一个int值存储queue中当前元素数，然后按照stack的逻辑全部取出再放回。不懂的话看链接：https://leetcode-cn.com/problems/implement-stack-using-queues/solution/yong-dui-lie-shi-xian-zhan-by-leetcode-solution/
        int size = queue.size();
        queue.offer(x);
        while(size > 0){
            queue.offer(queue.poll());
            size--;
        }
    }

    public int pop() {
        return queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
