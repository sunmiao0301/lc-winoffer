## 第一版
执行结果：
通过
显示详情
添加备注

执行用时：
2 ms
, 在所有 Java 提交中击败了
94.41%
的用户
内存消耗：
41.8 MB
, 在所有 Java 提交中击败了
5.15%
的用户
通过测试用例：
55 / 55
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums1.length; i++){
            set.add(nums1[i]);
        }
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < nums2.length; i++){
            if(set.contains(nums2[i])){
                arr.add(nums2[i]);
                set.remove(nums2[i]);
            }
        }
        int[] res = new int[arr.size()];
        for(int i = 0; i < arr.size(); i++){
            res[i] = arr.get(i);
        }
        return res;
    }
}

## 另外一种题解 用双指针+数组排序
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
