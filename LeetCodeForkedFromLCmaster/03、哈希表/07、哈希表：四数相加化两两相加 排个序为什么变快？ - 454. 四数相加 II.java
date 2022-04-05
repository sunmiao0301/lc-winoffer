//第一版 即使有HashMap 这个方法也太暴力了
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
    /*
    为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N
    我想到的方法是通过HashSet存储四元组
    但是要是直接硬for找的话就是500 * 500 * 500 * 500
    */
    }
}

//第二版 偷瞟了一下题解 
思路是将4数之和 (1+1+1+1) 转化为 (1+1) + (1+1) 
然后借助HashMap来存储（1+1）的结果
从而减少遍历次数
雀氏巧妙

一采用分为两组，HashMap 存一组，另一组和 HashMap 进行比对。
这样的话情况就可以分为三种：
HashMap 存一个数组，如 A。然后计算三个数组之和，如 BCD。时间复杂度为：O(n)+O(n^3)，得到 O(n^3).
HashMap 存三个数组之和，如 ABC。然后计算一个数组，如 D。时间复杂度为：O(n^3)+O(n)，得到 O(n^3).
HashMap存两个数组之和，如AB。然后计算两个数组之和，如 CD。时间复杂度为：O(n^2)+O(n^2)，得到 O(n^2).
所以用的两两分

按照这个思路自己写的结果如下：

执行结果：
通过
执行用时：
148 ms
, 在所有 Java 提交中击败了
45.43%
的用户
内存消耗：
38.6 MB
, 在所有 Java 提交中击败了
50.83%
的用户

class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
    /*
    为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N
    我想到的方法是通过HashSet存储四元组
    但是要是直接硬for找的话就是500 * 500 * 500 * 500
    */
    Map<Integer, Integer> map_1_2 = new HashMap<>();
    int len = nums1.length;
    for(int i = 0; i < len; i++){
        for(int j = 0; j < len; j++){
            if(map_1_2.containsKey(nums1[i] + nums2[j]))
                map_1_2.put(nums1[i] + nums2[j], map_1_2.get(nums1[i] + nums2[j]) + 1);//无需remove 直接put可以覆盖
            else
                map_1_2.put(nums1[i] + nums2[j], 1);
        }
    }
    int ret = 0;
    for(int i = 0; i < len; i++){
        for(int j = 0; j < len; j++){
            if(map_1_2.containsKey(-(nums3[i] + nums4[j])))
                ret += map_1_2.get(-(nums3[i] + nums4[j]));
        }
    }
    return ret;
    }
}

//第三版
根据第二版的结果来看
《四数相加》这个题的最佳解法应该不是用HashMap
然后看了用时排行榜
居然对四个数组排个序就快很多
从一个新的角度应用时间复杂度的知识——两个双循环快于一个四循环。

    //逐步减少n的数量
    //超出时间限制，如何优化？
    //先排序，然后重复项不需要重复计算，复用上一次计算结果即可·································为什么排个序会快很多
    //还是超出时间限制
    //再优化：对半分，第1级的结果数量先存入一个hashmap

执行结果：
通过
执行用时：
110 ms
, 在所有 Java 提交中击败了
95.87%
的用户
内存消耗：
38.4 MB
, 在所有 Java 提交中击败了
86.27%
的用户

class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
    /*
    为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N
    我想到的方法是通过HashSet存储四元组
    但是要是直接硬for找的话就是500 * 500 * 500 * 500
    */
    Arrays.sort(nums1);
    Arrays.sort(nums2);
    Arrays.sort(nums3);
    Arrays.sort(nums4);
    Map<Integer, Integer> map_1_2 = new HashMap<>();
    int len = nums1.length;
    for(int i = 0; i < len; i++){
        for(int j = 0; j < len; j++){
            if(map_1_2.containsKey(nums1[i] + nums2[j]))
                map_1_2.put(nums1[i] + nums2[j], map_1_2.get(nums1[i] + nums2[j]) + 1);//无需remove 直接put可以覆盖
            else
                map_1_2.put(nums1[i] + nums2[j], 1);
        }
    }
    int ret = 0;
    for(int i = 0; i < len; i++){
        for(int j = 0; j < len; j++){
            if(map_1_2.containsKey(-(nums3[i] + nums4[j])))
                ret += map_1_2.get(-(nums3[i] + nums4[j]));
        }
    }
    return ret;
    }
}
