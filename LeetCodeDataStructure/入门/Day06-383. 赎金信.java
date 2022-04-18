## 第一版 但是效率很差 第二版换成数组吧（哈希表换成数组是很常见的）
执行结果：
通过
显示详情
添加备注

执行用时：
12 ms
, 在所有 Java 提交中击败了
16.08%
的用户
内存消耗：
41.5 MB
, 在所有 Java 提交中击败了
51.32%
的用户
通过测试用例：
126 / 126
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map = new HashMap<>();
        //ransomNote 和 magazine 由小写英文字母组成
        for(int i = 0; i < magazine.length(); i++){
            char tmp = magazine.charAt(i);
            if(!map.containsKey(tmp)){
                map.put(tmp, 1);
            }
            else{
                map.put(tmp, map.get(tmp) + 1);
            }
        }
        for(int i = 0; i < ransomNote.length(); i++){
            char tmp = ransomNote.charAt(i);
            if(map.containsKey(tmp) && map.get(tmp) > 0){
                map.put(tmp, map.get(tmp) - 1);
            }
            else{
                return false;
            }
        }
        return true;
    }
}

## 第二版 换成数组 最优了
执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
99.92%
的用户
内存消耗：
41 MB
, 在所有 Java 提交中击败了
72.27%
的用户
通过测试用例：
126 / 126
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] maga = new int[26];
        for(int i = 0; i < magazine.length(); i++){
            int index = magazine.charAt(i) - 'a';
            maga[index]++;
        }
        for(int i = 0; i < ransomNote.length(); i++){
            int index = ransomNote.charAt(i) - 'a';
            if(maga[index] > 0){
                maga[index]--;
            }
            else{
                return false;
            }
        }
        return true;
    }
}
