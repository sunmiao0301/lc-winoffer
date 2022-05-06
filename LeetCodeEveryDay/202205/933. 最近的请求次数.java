## 第一版 通过 效率一般 --- 但是和标准题解一致

执行结果：
通过
显示详情
添加备注

执行用时：
20 ms
, 在所有 Java 提交中击败了
51.49%
的用户
内存消耗：
49.8 MB
, 在所有 Java 提交中击败了
24.42%
的用户
通过测试用例：
68 / 68

class RecentCounter {
    Queue<Integer> queue;
    //根据题意可知 ping一定不为0 因为是左闭右闭 所以一定有这一次的ping
    public RecentCounter() {
        queue = new LinkedList<>();
    }
    
    public int ping(int t) {
        queue.offer(t);
        while(queue.peek() < t - 3000){
            queue.poll();
        }
        return queue.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */ 
