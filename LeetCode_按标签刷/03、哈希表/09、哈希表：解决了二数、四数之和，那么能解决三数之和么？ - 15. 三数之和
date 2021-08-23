//第一版 没做到去重
输入 [-1,0,1,2,-1,-4]
输出 [[1,-1,0],[0,-1,1],[-1,-1,2],[2,-1,-1],[-1,0,1],[1,0,-1],[0,1,-1],[-1,2,-1]]
预期结果 [[-1,-1,2],[-1,0,1]]

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
    /*
    一个包含 n 个整数的数组 nums 注意不是三个数组
    重复三元组 包括换个顺序、组成相同的三元组、也就是说答案是组合 不是排列
    两个for嵌套循环找bc当abc=0去找a<a, frequency>
    如果bc中有等于a的 temp++
    然后看<a, frequency>的fre是否大于temp 大于就入list存大list
    */
    Map<Integer, Integer> map = new HashMap<>();
    List<List<Integer>> ret = new LinkedList<>();
    for(int i = 0; i < nums.length; i++){
        if(map.containsKey(nums[i]))
            map.put(nums[i], map.get(nums[i]) + 1);
        else
            map.put(nums[i], 1);
    }
    for(int i = 0; i < nums.length; i++){
        for(int j = i + 1; j < nums.length; j++){
            int a = - (nums[i] + nums[j]);
            if(map.containsKey(a)){
                int temp = 1;
                if(nums[i] == a)
                    temp++;
                if(nums[j] == a)
                    temp++;
                if(map.get(a) >= temp){
                    List<Integer> list = new ArrayList<>();
                    list.add(a);
                    list.add(nums[i]);
                    list.add(nums[j]);
                    ret.add(list);
                    //list.clear();//················注意不能clear()
                }
            }
        }
    }
    for(int i = 0; i < ret.size(); i++){
        //去重？
    }
    return ret;
    }
}

//第二版 偷瞟一眼题解是 《双指针》
其实去重才是最大的问题 如果去重 或者说 如果不出现重复？
        /*
        这一题用双指针解决 关键点在于要先排序数组！
        排序之后 双指针开始找 直到l == r再结束
        这样不会发生重复的原因是按照排序后数组找的三元组 
        一定是从小到大排列的 如[-1, 0, 1]
        所以只要i不同 且后面两个值不是重复加入list 就一定不会重
        所以需要注意双指针lr在移动的时候要保证不与之前一个已经存入list的重复
        */
看了思路之后自己写的 效率还行
执行结果：
通过
执行用时：
22 ms
, 在所有 Java 提交中击败了
72.75%
的用户
内存消耗：
42.7 MB
, 在所有 Java 提交中击败了
21.53%
的用户

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 2; i++){
            if(i > 0 && nums[i] == nums[i-1])continue;
            int l = i + 1;
            int r = nums.length - 1;
            while(l < r){//&& l <= nums.length && l < nums.length
                if(nums[i] + nums[l] + nums[r] > 0)
                    r--;
                else if(nums[i] + nums[l] + nums[r] < 0)
                    l++;
                else{
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[l]);
                    list.add(nums[r]);
                    ret.add(list);
                    r--;
                    while(l < r && nums[r] == nums[r+1])
                        r--;
                }
            }
        }
        return ret;
    }
}

//第三版 标准题解
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return result;
            }

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;
            while (right > left) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    while (right > left && nums[right] == nums[right - 1]) right--;
                    while (right > left && nums[left] == nums[left + 1]) left++;
                    
                    right--; 
                    left++;
                }
            }
        }
        return result;
    }
}
