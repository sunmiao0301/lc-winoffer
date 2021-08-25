//第一版 因为之前在剑指offer里面做过 所以最终还是做出来了 但是还是很难 效率还行 就不优化了
执行结果：
通过
执行用时：
31 ms
, 在所有 Java 提交中击败了
76.61%
的用户
内存消耗：
52.3 MB
, 在所有 Java 提交中击败了
87.28%
的用户
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
    Deque<Integer> deque = new LinkedList<>();
    //deque单调递减
    //int max = nums[0];
    int[] ret = new int[nums.length - k + 1];
    for(int i = 0; i < k; i++){
        if(deque.isEmpty() || nums[i] <= deque.peekLast()){
            deque.addLast(nums[i]);
            continue;
        }
        while(!deque.isEmpty() && nums[i] > deque.peekLast()){
            deque.pollLast();
        }
        deque.addLast(nums[i]);
    }
    ret[0] = deque.peekFirst();
    for(int i = k; i < nums.length; i++){
        if(nums[i - k] == deque.peekFirst()){
            deque.pollFirst();
        }
        if(deque.isEmpty() || nums[i] <= deque.peekLast()){
            deque.addLast(nums[i]);
            ret[i - k + 1] = deque.peekFirst();
            continue;
        }
        while(!deque.isEmpty() && nums[i] > deque.peekLast()){
            deque.pollLast();
        }
        deque.addLast(nums[i]);
        ret[i - k + 1] = deque.peekFirst();
    }
    return ret;
    }
}
