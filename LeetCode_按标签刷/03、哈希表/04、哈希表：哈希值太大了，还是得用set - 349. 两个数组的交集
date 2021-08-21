//第一版 用的HashSet 一遍过 效率不错 由于只涉及两个数组 比较简单
执行结果：
通过
执行用时：
2 ms
, 在所有 Java 提交中击败了
94.53%
的用户
内存消耗：
38.2 MB
, 在所有 Java 提交中击败了
97.00%
的用户
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
    //交集 不是 并集
    Set<Integer> set = new HashSet<>();//nums1.length
    for(int i = 0; i < nums1.length; i++){
        set.add(nums1[i]);
    }
    List<Integer> ret = new ArrayList<>();
    for(int i = 0; i < nums2.length; i++){
        if(set.contains(nums2[i])){
            ret.add(nums2[i]);
            set.remove(nums2[i]);
        }
    }
    //动态数组转数组怎么快？
    int[] arr = new int[ret.size()];
    for(int i = 0; i < ret.size(); i++){
        arr[i] = ret.get(i);
    }
    return arr; 
    }
}

//标准题解1 两个集合
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> resSet = new HashSet<>();
        //遍历数组1
        for (int i : nums1) {
            set1.add(i);
        }
        //遍历数组2的过程中判断哈希表中是否存在该元素
        for (int i : nums2) {
            if (set1.contains(i)) {
                resSet.add(i);
            }
        }
        int[] resArr = new int[resSet.size()];
        int index = 0;
        //将结果几何转为数组
        for (int i : resSet) {
            resArr[index++] = i;
        }
        return resArr;
    }
}
那么用数组来做哈希表也是不错的选择，例如242. 有效的字母异位词
但是要注意，使用数组来做哈希的题目，是因为题目都限制了数值的大小。
而这道题目没有限制数值的大小，就无法使用数组来做哈希表了。
而且如果哈希值比较少、特别分散、跨度非常大，使用数组就造成空间的极大浪费。

//标准题解2 排序+双指针
执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
99.92%
的用户
内存消耗：
38.5 MB
, 在所有 Java 提交中击败了
66.58%
的用户
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) {
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                // 保证加入元素的唯一性
                if (index == 0 || num1 != intersection[index - 1]) {
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}
