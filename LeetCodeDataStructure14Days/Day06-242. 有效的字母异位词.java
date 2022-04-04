## 和上一题没什么区别 加一个判断总字数是否相同即可
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length())
            return false;
        int[] alphabet = new int[26];
        for(int i = 0; i < s.length(); i++){
            int index = s.charAt(i) - 'a';
            alphabet[index]++;
        }
        for(int i = 0; i < t.length(); i++){
            int index = t.charAt(i) - 'a';
            if(alphabet[index] > 0){
                alphabet[index]--;
            }
            else{
                return false;
            }
        }
        return true;
    }
}
