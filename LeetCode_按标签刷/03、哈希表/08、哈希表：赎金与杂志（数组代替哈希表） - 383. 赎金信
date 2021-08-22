//第一版 一遍过 但是效率很差
猜测优化思路是“你可以假设两个字符串均只含有小写字母。”
所以可以通过数组代替哈希表

执行结果：
通过
执行用时：
13 ms
, 在所有 Java 提交中击败了
19.32%
的用户
内存消耗：
39.1 MB
, 在所有 Java 提交中击败了
17.95%
的用户

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();
        int i = 0;
        for(i = 0; i < magazine.length(); i++){
            if(map.containsKey(magazine.charAt(i)))
                map.put(magazine.charAt(i), map.get(magazine.charAt(i)) + 1);
            else
                map.put(magazine.charAt(i), 1);
        }
        for(i = 0; i < ransomNote.length(); i++){
            if(map.containsKey(ransomNote.charAt(i))){
                if(map.get(ransomNote.charAt(i)) > 0)
                    map.put(ransomNote.charAt(i), map.get(ransomNote.charAt(i)) - 1);
                else 
                    break;
            }
            else
                break;
        }
        if(i == ransomNote.length())
            return true;
        return false;
    }
}

//第二版 一遍过 果然是用的数组 效率最高
执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
99.89%
的用户
内存消耗：
38.6 MB
, 在所有 Java 提交中击败了
62.40%
的用户
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] mazg = new int[26];
        for(int i = 0; i < magazine.length(); i++){
            mazg[magazine.charAt(i) - 'a']++;
        }
        for(int i = 0; i < ransomNote.length(); i++){
            if(mazg[ransomNote.charAt(i) - 'a'] > 0)
                mazg[ransomNote.charAt(i) - 'a']--;
            else
                return false;
        }
        return true;
    }
}
