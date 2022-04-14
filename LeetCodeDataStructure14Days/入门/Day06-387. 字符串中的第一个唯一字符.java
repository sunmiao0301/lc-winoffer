## 第一版 思路最佳 -- 和出现次数有关的，不要犹豫，hash
执行结果：
通过
显示详情
添加备注

执行用时：
7 ms
, 在所有 Java 提交中击败了
70.21%
的用户
内存消耗：
42.1 MB
, 在所有 Java 提交中击败了
6.27%
的用户
通过测试用例：
105 / 105
class Solution {
    public int firstUniqChar(String s) {
        //s 只包含小写字母
        //HashMap在Key的种类一定的情况下，可以优化为Array
        int[] arr = new int[26];
        for(int i = 0; i < s.length(); i++){
            //s.CharAt(i) - 'a';
            arr[s.charAt(i) - 'a'] = arr[s.charAt(i) - 'a'] + 1;
        }
        for(int i = 0; i < s.length(); i++){
            if(arr[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }
}
