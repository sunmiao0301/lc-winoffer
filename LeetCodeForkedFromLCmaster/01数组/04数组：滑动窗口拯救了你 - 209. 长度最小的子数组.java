2nd
class Solution {
    public int[] sortedSquares(int[] nums) {
        //我的思路是二分先找到正负分割点，然后左右轮流按照绝对值大小进行平方
        //但是我思路僵了，不一定要先从最小到最大，从最大到最小构造新数组的话，就可以直接双指针从0和n-1位置就行了
        int l = 0;
        int r = nums.length - 1;
        int index = r;
        int[] res = new int[nums.length];
        while(l <= r){
            if(Math.abs(nums[l]) > Math.abs(nums[r])){
                res[index] = nums[l] * nums[l];
                index--;
                l++;
            }
            else{
                res[index] = nums[r] * nums[r];
                index--;
                r--;
            }
        }
        return res;
    }
}

//第一版
非递减？啥意思，是非严格递增的意思么 好像是的
四种序列的简单区别 
递增排列：1,2,3,4,5 
递减排列：5,4,3,2,1 
非递减排列：1,2,3,3,4,5 
非递增排列：6,5,5,4,4,3,2,1 
执行结果：
通过 但是效率一般
执行用时：
3 ms
, 在所有 Java 提交中击败了
15.88%
的用户
内存消耗：
39.9 MB
, 在所有 Java 提交中击败了
86.96%
的用户
class Solution {
    public int[] sortedSquares(int[] nums) {
    /*
    进阶：请你设计时间复杂度为 O(n) 的算法解决本问题
    关键是排出来得还是非递减排序 那还是用ArrayList比较香
    */
    ArrayList<Integer> list = new ArrayList<>();
    for(int i = 0, j = nums.length - 1; i <= j; ){
        if(Math.abs(nums[i]) >= Math.abs(nums[j])){
            list.add((int)Math.pow(nums[i], 2));
            i++;
        }
        else{
            list.add((int)Math.pow(nums[j], 2));
            j--;
        }
    }
    int[] ret = new int[list.size()];
    for(int i = list.size() - 1; i >= 0; i--){
        ret[list.size() - 1 - i] = list.get(i);
    }
    return ret;
    }
}

//第二版 换了个思路 先找绝对值最小值 然后双指针向两边扩散填入要返回的数组int[] ret
执行结果：
通过
执行用时：
2 ms
, 在所有 Java 提交中击败了
45.68%
的用户
内存消耗：
40 MB
, 在所有 Java 提交中击败了
78.98%
的用户
class Solution {
    public int[] sortedSquares(int[] nums) {
    /*
    试一下先找到最小值
    */
    int i = 0;
    for( ; i < nums.length - 1; i++){
        if(Math.abs(nums[i + 1]) > Math.abs(nums[i]))
            break;
    }
    i--;
    int j = i + 1;
    int index = 0;
    int[] ret = new int[nums.length];
    while(i >= 0 || j <= nums.length - 1){//i往左 j往右
        if(i < 0){
            ret[index++] = (int)Math.pow(nums[j], 2);
            j++;         
        }
        else if(j > nums.length - 1){
            ret[index++] = (int)Math.pow(nums[i], 2);
            i--;                
        }
        else if(Math.abs(nums[i]) > Math.abs(nums[j])){
            ret[index++] = (int)Math.pow(nums[j], 2);
            j++;            
        }
        else if(Math.abs(nums[i]) <= Math.abs(nums[j])){
            ret[index++] = (int)Math.pow(nums[i], 2);
            i--; 
        }
    }
    return ret;
    }
}

//第三版
执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
39.8 MB
, 在所有 Java 提交中击败了
93.92%
的用户
把一二的思路结合一下，就能100%了，就是双指针从头尾开始，然后另外维护一个返回的数组的指针初始化指向数组最后一位
需要注意的是，求平方的时候，pow函数还不如直接 * 快，因为得到还要(int)，比直接 * 慢多了！
class Solution {
    public int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0, j = n - 1, pos = n - 1; i <= j;) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                ans[pos] = nums[i] * nums[i];
                ++i;
            } else {
                ans[pos] = nums[j] * nums[j];
                --j;
            }
            --pos;
        }
        return ans;
    }
}
