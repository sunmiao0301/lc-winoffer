## 第一版

但是没考虑到paragraph不只由字母组成，所以出错于：
输入：
"a, a, a, a, b,b,b,c, c"
["a"]
输出：
"c"
预期结果：
"b"

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        //题目保证至少有一个词不在禁用列表中，而且答案唯一。
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < paragraph.length(); i++){
            if(paragraph.charAt(i) == ' '){
                String tmp = sb.toString();
                map.put(tmp, map.getOrDefault(tmp, 0) + 1);
                sb.delete(0, sb.length());
            }
            else if(paragraph.charAt(i) >= 'A' && paragraph.charAt(i) <= 'Z'){//是大写字母
                sb.append((char)(paragraph.charAt(i) - 'A' + 'a'));
            }
            else if(paragraph.charAt(i) >= 'a' && paragraph.charAt(i) <= 'z'){
                sb.append(paragraph.charAt(i));
            }
            else{
                continue;
            }
        }

                String tmp = sb.toString();
                map.put(tmp, map.getOrDefault(tmp, 0) + 1);
        
        
        String res = "";
        int max = 0;
        for(int i = 0; i < banned.length; i++){
            if(map.containsKey(banned[i])){
                map.put(banned[i], 0);
            }
        }
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            // (entry.getValue())
            if(entry.getValue() > max){
                max = entry.getValue();
                res = entry.getKey();
            }
        }
        return res;
    }
}

## 第二版 主要就是注意这个代码！！ ： if(tmp != "")//关键！

执行结果：
通过
显示详情
添加备注

执行用时：
8 ms
, 在所有 Java 提交中击败了
48.54%
的用户
内存消耗：
41 MB
, 在所有 Java 提交中击败了
71.02%
的用户
通过测试用例：
47 / 47

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        //题目保证至少有一个词不在禁用列表中，而且答案唯一。
        HashMap<String, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < paragraph.length(); i++){
            if(paragraph.charAt(i) >= 'A' && paragraph.charAt(i) <= 'Z'){//是大写字母
                sb.append((char)(paragraph.charAt(i) - 'A' + 'a'));
            }
            else if(paragraph.charAt(i) >= 'a' && paragraph.charAt(i) <= 'z'){
                sb.append(paragraph.charAt(i));
            }
            else{
                String tmp = sb.toString();
                if(tmp != "")//关键！
                    map.put(tmp, map.getOrDefault(tmp, 0) + 1);
                sb.delete(0, sb.length());
            }
        }

        // String ret = "";
        // for(Map.Entry<String, Integer> entry : map.entrySet()){
        //     // (entry.getValue())
        //     ret += entry.getValue();
        // }     

        String tmp = sb.toString();
        if(tmp != "")
            map.put(tmp, map.getOrDefault(tmp, 0) + 1);
        
        String res = "";
        int max = 0;
        
        for(int i = 0; i < banned.length; i++){
            if(map.containsKey(banned[i])){
                map.put(banned[i], 0);
            }
        }

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            // (entry.getValue())
            if(entry.getValue() > max){
                max = entry.getValue();
                res = entry.getKey();
            }
        }
        return res;
    }
}
