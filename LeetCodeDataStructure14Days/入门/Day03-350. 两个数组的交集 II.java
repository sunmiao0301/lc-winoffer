## 第一版 效率很差 
/**
其中A处的代码值得说明一下，为什么能用map1.put(k, Math.min(v, 0)); 而用remove()的时候就会报错为ConcurrentModificationException异常;
事实上，对Vector、ArrayList在迭代的时候如果同时对其进行修改就会抛出java.util.ConcurrentModificationException异常。
具体分析可见：https://www.cnblogs.com/dolphin0520/p/3933551.html
*/
执行结果：
通过
显示详情
添加备注

执行用时：
6 ms
, 在所有 Java 提交中击败了
7.96%
的用户
内存消耗：
41.2 MB
, 在所有 Java 提交中击败了
60.21%
的用户
通过测试用例：
56 / 56
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        for(int i = 0; i < nums1.length; i++){
            if(!map1.containsKey(nums1[i])){
                map1.put(nums1[i], 1);
            }
            else{
                //map1.get(num1[i])
                map1.put(nums1[i], map1.get(nums1[i]) + 1);
            }
        }
        for(int i = 0; i < nums2.length; i++){
            if(!map2.containsKey(nums2[i])){
                map2.put(nums2[i], 1);
            }
            else{
                //map2.get(num1[i])
                map2.put(nums2[i], map2.get(nums2[i]) + 1);
            }
        }
        //int arrLen = 0;
        map1.forEach((k,v) -> {
            if(map2.containsKey(k)){
                map1.put(k, Math.min(v, map2.get(k)));
            }
            else{
                map1.put(k, Math.min(v, 0));//·········································A
                //map1.remove(k);
            }
        });
        List<Integer> arr = new ArrayList<>();
        map1.forEach((k, v) -> {
            for(int i = v; i > 0; i--){
                arr.add(k);
            }
        });
        int[] res = new int[arr.size()];
        for(int i = 0; i < arr.size(); i++){
            res[i] = arr.get(i);
        }
        return res;
    }
}

## 题解之与我的第一版思路一致，但是更聪明的做法（我图省事，用了两个哈希表）：
首先遍历第一个数组，并在哈希表中记录第一个数组中的每个数字以及对应出现的次数
然后遍历第二个数组，对于第二个数组中的每个数字，如果在哈希表中存在这个数字，则将该数字添加到答案，并减少哈希表中该数字出现的次数。

效率稍好一些
执行结果：
通过
显示详情
添加备注

执行用时：
3 ms
, 在所有 Java 提交中击败了
39.32%
的用户
内存消耗：
41.6 MB
, 在所有 Java 提交中击败了
19.84%
的用户
通过测试用例：
56 / 56

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }
        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}

## 题解的另外一个思路： 排序+双指针
执行结果：
通过
显示详情
添加备注

执行用时：
2 ms
, 在所有 Java 提交中击败了
91.71%
的用户
内存消耗：
41.7 MB
, 在所有 Java 提交中击败了
7.93%
的用户
通过测试用例：
56 / 56
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}

p.s. 如果nums2的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中。
那么就无法高效地对nums2进行排序，因此不推荐后一种思路。在方法一中,nums2只关系到查询操作，因此每次读取nums2中的一部分数据，并进行处理即可。
