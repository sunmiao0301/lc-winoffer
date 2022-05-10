## 第一版 写着好玩 

class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}

## 题解 ---> 实际上有两种更好的思路：
1）方法一：通过 partition 减治，这是快速排序 partition 的应用；
2）方法二：动态求出最值元素，是「优先队列」的应用。

写堆的方法之前，首先明确几个概念：
大根堆也叫大顶堆
小根堆也叫小顶堆
大根堆用于升序排序（所以求最小的k个数用大根堆）
小根堆用于降序排序（所以求最大的k个数（常见的topk问题，基本都是求最大的k个数）用小根堆）。
堆排序的时间复杂度是 O(NlogN)，空间复杂度是 O(1),所以在海量数据及内存不足的条件下，该排序方法适用。

如果对 堆 以及 堆排序 的概念不清楚，将导致你不能理解 为什么求最大的第k个数需要用到小顶堆

p.s. 这题还有变体 ---> 最大的k个数 （注意与最大的第k个数的不同）---> 题见"https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/solution/3chong-jie-fa-miao-sha-topkkuai-pai-dui-er-cha-sou/

## 题解之调用堆API --- p.s.  PriorityQueue 默认是小根堆，大根堆需要重写比较器
class Solution {
    public int findKthLargest(int[] nums, int k) {
        //优先级队列，就是个小根堆
        //固定优先级队列的长度为k,那么最后留在优先级队列里面的数就是最大的k个数。
        PriorityQueue<Integer> minTree = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            //每个数都加入到小根堆中。
            minTree.add(nums[i]);
            //如果加入之后堆的大小 大于k，那么就弹出堆顶。
            //这样的话每次加入之后最大的都在下面，弹出的都是小的数。
            if (minTree.size() > k) minTree.poll();
        }
        //堆顶的数就是第k大的。
        return minTree.peek();
    }
}
