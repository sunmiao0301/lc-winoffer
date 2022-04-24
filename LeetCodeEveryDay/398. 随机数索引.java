## 第一版 我的解法如下 但是没通过 "想了一下发现错在哪了 --> 我把数组修改了 但是数组实际上是不能修改的，因为最后返回的对应的是原数组索引）

执行结果：
解答错误
显示详情
添加备注

通过测试用例：
4 / 14
输入：
["Solution","pick"] [[[-1002464978,-68020051,-1462267596,1317081354,-888922907,709696102,1566377039,-1568877186,1975821187,-242325840,317741131,1029545763,1955280671,1893328244,2093616131,-482518759,-714187586,275678785,-2087905559,-1133540364,-2025446997,-516336286,-1251713464,-672993494,397763492,-1151463861,-423085760,2060203531,-1216235733,1432526354,-167581936,1605950372,834406161,1968791536,-45297112,738141992,-1228566543,-364754177,-360751980,-983948383,412673963,725918816,-310613135,370644200,642313055,-1970525419,-1697058904,1016585295,-1450613598,-1841144696,821057663,-970457088,-501524242,-309374753,-1200613413,615121420,-1918986005,1848134926,-1349226362,-1372431586,1674029384,131178055,1292801910,227047929,-801771049,775453731,-1626213089,186338334,-1192847875,-730944471,-913618417,1638202134,-1273573202,-941015785,1115202197,741802099,958112505,-1202787942,-232190071,1645261758,-1507846737,-816141589,-206408385,427626634,-848661292,-2124980648,-1606808617,-583788369,324063966,-672941063,1191
查看全部
输出：
[null,6415]
预期结果：
[null,4857]

class Solution {
    //数组大小可能非常大。 
    //使用太多额外空间的解决方案将不会通过测试
    //可以假设给定的数字一定存在于数组中。
    Map<Integer, int[]> map;// = new HashMap<>();

    public Solution(int[] nums) {
        Arrays.sort(nums);
        map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                int[] tmp = map.get(nums[i]);
                tmp[1] = i;
            }
            else{
                int[] tmp = new int[2];
                tmp[0] = i;
                map.put(nums[i], tmp);
            }
        }
    }
    
    public int pick(int target) {
        int[] arr = map.get(target);
        int left = arr[0];
        int right = arr[1];
        if(right <= left){
            return left;
        }
        else{
            Random rand=new Random();
            return rand.nextInt(right - left + 1) + left;
        }
    }
}

// int randNumber =rand.nextInt(MAX - MIN + 1) + MIN;

// Random rand=new Random();
//         int n1=rand.nextInt(100);//返回值在范围[0,100) 即[0,99]
//         int n2=rand.nextInt(100)+1;//[1,100]内的随机整数
//         int n3=rand.nextInt(80)+10;//[10,89]内的随机整

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
 
## 题解的暴力法：（只不过他是把所有值存入链表，我是先把nums数组排个序，然后将
class Solution {
    Map<Integer, List<Integer>> pos;
    Random random;

    public Solution(int[] nums) {
        pos = new HashMap<Integer, List<Integer>>();
        random = new Random();
        for (int i = 0; i < nums.length; ++i) {
            pos.putIfAbsent(nums[i], new ArrayList<Integer>());
            pos.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> indices = pos.get(target);
        return indices.get(random.nextInt(indices.size()));
    }
}
复杂度分析：
时间复杂度：初始化为 O(n)，pick 为 O(1)，其中 n 是 nums 的长度。
空间复杂度：O(n)。我们需要 O(n) 的空间存储 n 个下标。

 
## 题解 第一次做这种题，自己想的话，基本上想不到 --> 蓄水池抽样

水塘抽样
如果数组以文件形式存储（读者可假设构造函数传入的是个文件路径），且文件大小远超内存大小，我们是无法通过读文件的方式，将所有下标保存在内存中的，因此需要找到一种空间复杂度更低的算法。

我们可以设计如下算法实现 pick 操作：遍历 nums，当我们 "第 i 次" 遇到值为 target 的元素时，随机选择区间 [0,i) 内的一个整数，如果其等于 0，则将返回值置为该元素的下标，否则返回值不变。
设 nums 中有 k 个值为 target 的元素，该算法会保证这 k 个元素的下标成为最终返回值的概率均为 "1\k"

证明见题解地址 "https://leetcode-cn.com/problems/random-pick-index/solution/sui-ji-shu-suo-yin-by-leetcode-solution-ofsq/

class Solution {
    int[] nums;
    Random random;

    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    public int pick(int target) {
        int ans = 0;
        for (int i = 0, cnt = 0; i < nums.length; ++i) {
            if (nums[i] == target) {
                ++cnt; // 第 cnt 次遇到 target
                if (random.nextInt(cnt) == 0) {
                    ans = i;
                }
            }
        }
        return ans;
    }
}
时间复杂度：初始化为 O(1)，pick 为 O(n)，其中 n 是 nums 的长度。
空间复杂度：O(1)。我们只需要常数的空间保存若干变量。
 
 
 
 
 
