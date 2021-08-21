//第一版 
由于对hashmap的结构的不熟练，我想到的办法是一个HashMap<String, int[]>，但是实际上hashmap好像结构就是如此
标准题解思路和我的思路一样
但是标准题解是一个一个来，我是想一次性完成
（题解思路是先搞个A，然后AB比，AB结果和C比，ABC结果与D比
我的思路是ABCD一起比）
看了题解之后我写的：
执行结果：
通过
执行用时：
3 ms
, 在所有 Java 提交中击败了
90.10%
的用户
内存消耗：
38.6 MB
, 在所有 Java 提交中击败了
52.03%
的用户
class Solution {
    public List<String> commonChars(String[] words) {
        int[] hash = new int[26];
        List<String> ret = new ArrayList<>();
        int len = words.length;
        //if(len == 0)
        for(int i = 0; i < words[0].length(); i++){
            hash[words[0].charAt(i) - 'a']++;
        }
        int[] hashOther = new int[26];
        for(int i = 1; i < len; i++){
            for(int j = 0; j < words[i].length(); j++){
                hashOther[words[i].charAt(j) - 'a']++;
            }
            for(int k = 0; k < 26; k++){
                hash[k] = Math.min(hash[k], hashOther[k]);
                hashOther[k] = 0;
            }
        }
        for(int i = 0; i < 26; i++){
            for(int j = 0; j < hash[i]; j++){
                ret.add(Character.toString((char)(i + 'a')));·····························推荐用String.valueOf(char)而不是Character.toString(char)
            }
        }
        return ret;
    }
}

//标准题解
class Solution {
    public List<String> commonChars(String[] A) {
        List<String> result = new ArrayList<>();
        if (A.length == 0) return result;
        int[] hash= new int[26]; // 用来统计所有字符串里字符出现的最小频率
        for (int i = 0; i < A[0].length(); i++) { // 用第一个字符串给hash初始化
            hash[A[0].charAt(i)- 'a']++;
        }
        // 统计除第一个字符串外字符的出现频率
        for (int i = 1; i < A.length; i++) {
            int[] hashOtherStr= new int[26];
            for (int j = 0; j < A[i].length(); j++) {
                hashOtherStr[A[i].charAt(j)- 'a']++;
            }
            // 更新hash，保证hash里统计26个字符在所有字符串里出现的最小次数
            for (int k = 0; k < 26; k++) {
                hash[k] = Math.min(hash[k], hashOtherStr[k]);
            }
        }
        // 将hash统计的字符次数，转成输出形式
        for (int i = 0; i < 26; i++) {
            while (hash[i] != 0) { // 注意这里是while，多个重复的字符
                char c= (char) (i+'a');
                result.add(String.valueOf(c));
                hash[i]--;
            }
        }
        return result;
    }
}

