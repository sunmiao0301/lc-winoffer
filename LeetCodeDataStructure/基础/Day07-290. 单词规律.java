## 第一版 但是错在了如下样例上

执行结果：
解答错误
显示详情
添加备注

通过测试用例：
33 / 36
输入：
"abba"
"dog dog dog dog"
输出：
true
预期结果：
false

class Solution {
    public boolean wordPattern(String pattern, String s) {
        //pattern 里的每个字母和字符串 str 中的每个非空单词
        //s 只包含小写英文字母和 ' '
        HashMap<Character, String> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++){
            char tmp = s.charAt(i);
            if(tmp >= 'a' && tmp <= 'z'){
                sb.append(tmp);
            }
            else{
                if(sb.toString().equals("")){
                    continue;
                }
                else{
                    list.add(sb.toString());
                    sb.delete(0, sb.length());
                }
            }
        }
        if(!sb.toString().equals("")){
            list.add(sb.toString());
        }
        if(pattern.length() != list.size()){
            return false;
        }
        for(int i = 0; i < pattern.length(); i++){
            char tmp = pattern.charAt(i);
            if(!map.containsKey(tmp)){
                map.put(tmp, list.get(i));
            }
            else{
                if(!map.get(tmp).equals(list.get(i)))
                    return false;
            }
        }
        return true;
    }
}

## 第二版

执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
54.24%
的用户
内存消耗：
38.8 MB
, 在所有 Java 提交中击败了
81.15%
的用户
通过测试用例：
36 / 36

class Solution {
    public boolean wordPattern(String pattern, String s) {
        //pattern 里的每个字母和字符串 str 中的每个非空单词
        //s 只包含小写英文字母和 ' '
        HashMap<Character, String> mapPToS = new HashMap<>();
        HashMap<String, Character> mapSToP = new HashMap<>();
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++){
            char tmp = s.charAt(i);
            if(tmp >= 'a' && tmp <= 'z'){
                sb.append(tmp);
            }
            else{
                if(sb.toString().equals("")){
                    continue;
                }
                else{
                    list.add(sb.toString());
                    sb.delete(0, sb.length());
                }
            }
        }
        if(!sb.toString().equals("")){
            list.add(sb.toString());
        }


        if(pattern.length() != list.size()){
            return false;
        }

        for(int i = 0; i < pattern.length(); i++){
            char cTmp = pattern.charAt(i);
            String sTmp = list.get(i);

            if(!mapPToS.containsKey(cTmp) && !mapSToP.containsKey(sTmp)){
                mapPToS.put(cTmp, sTmp);
                mapSToP.put(sTmp, cTmp);
            }
            else if(mapPToS.containsKey(cTmp) && mapSToP.containsKey(sTmp)){
                if(mapPToS.get(cTmp).equals(sTmp) && mapSToP.get(sTmp) == cTmp){
                    continue;
                }
                else{
                    return false;
                }
            }
            else{
                return false;
            }
        }
        return true;
    }
}

## 题解

在本题中，我们需要判断字符与字符串之间是否恰好一一对应。即任意一个字符都对应着唯一的字符串，任意一个字符串也只被唯一的一个字符对应。在集合论中，这种关系被称为「双射」。两个hashmap，用空间换时间

class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<String, Character> str2ch = new HashMap<String, Character>();
        Map<Character, String> ch2str = new HashMap<Character, String>();
        int m = str.length();
        int i = 0;
        for (int p = 0; p < pattern.length(); ++p) {
            char ch = pattern.charAt(p);
            if (i >= m) {
                return false;
            }
            int j = i;
            while (j < m && str.charAt(j) != ' ') {
                j++;
            }
            String tmp = str.substring(i, j);
            if (str2ch.containsKey(tmp) && str2ch.get(tmp) != ch) {
                return false;
            }
            if (ch2str.containsKey(ch) && !tmp.equals(ch2str.get(ch))) {
                return false;
            }
            str2ch.put(tmp, ch);
            ch2str.put(ch, tmp);
            i = j + 1;
        }
        return i >= m;
    }
}

