//第一版 一遍过 效率还行 但是没100%
通过这一题，不难认识到：当hashmap中键Key的数量比较小且每个Key都已知时，可以将hashmap转化为数组array
比如这一题中 小写字母的ascii码是97-122
那么直接建立一个26容量的数组存储这26个字母的出现次数即可，而不必用hashmap。

执行结果：
通过
执行用时：
2 ms
, 在所有 Java 提交中击败了
94.78%
的用户
内存消耗：
38.4 MB
, 在所有 Java 提交中击败了
87.73%
的用户
class Solution {
    /*
    通过这一题，我能感觉到：当hashmap中键Key的数量比较小且每个Key都已知时，可以将hashmap转化为数组array
    字符串中的是不是都是ASCII码？
    题目给出：s 和 t 仅包含小写字母a-z（97-122）
    进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
    */
    public boolean isAnagram(String s, String t) {
        int len_s = s.length();
        int len_t = t.length();
        if(len_s != len_t)return false;
        int[] arr = new int[26];
        for(int i = 0; i < len_s; i++){
            arr[s.charAt(i) - 97]++;
        }
        for(int i = 0; i < len_t; i++){
            arr[t.charAt(i) - 97]--;
        }
        for(int i = 0; i < 26; i++){
            if(arr[i] != 0)
                return false;
        }
        return true;
    }
}
