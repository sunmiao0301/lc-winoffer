## 写的是对的 但是在最后几个测试样例的时候超时了 需要优化

111 / 115 个通过测试用例
状态：超出时间限制

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        //请你将 字母异位词 组合在一起 返回
        List<List<String>> res = new ArrayList<>();

        for(int i = 0; i < strs.length; i++){//最外层的循环
            String tmp = strs[i];
            if(tmp.equals("0")){
                continue;
            }
            int[] map = new int[26];
            
            for(int j = 0; j < tmp.length(); j++){//得到最外层循环的tmp的map表
                map[tmp.charAt(j) - 'a']++;
            }

            List<String> listTmp = new ArrayList<>();
            // if(!tmp.equals("0"){
                listTmp.add(tmp);
            // }

            for(int k = i + 1; k < strs.length; k++){//内存循环，从i开始
                if(strs[k].equals("0")){
                    continue;
                }
                int[] mapClone = (int[])map.clone();
                if(strs[k].length() == tmp.length()){//至少得字符串长度相同才可能是异位词
                    int l = 0;
                    for(l = 0; l < strs[k].length(); l++){//开始检查是否是异位词
                        mapClone[strs[k].charAt(l) - 'a']--;
                        if(mapClone[strs[k].charAt(l) - 'a'] < 0){
                            break;
                        }
                    }
                    if(l != strs[k].length()){
                        continue;
                    }
                    String newString = new String(strs[k].substring(0, strs[k].length()));
                    listTmp.add(newString);
                    strs[k] = "0";
                }
                else{
                    continue;
                }
            }
            res.add(listTmp);
            strs[i] = "0";//其实这一步是多余的
        }
        return res;
    }
}

## 第二版 优化了一些冗余的部分 但是时间还是不够快

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        //请你将 字母异位词 组合在一起 返回
        List<List<String>> res = new ArrayList<>();

        for(int i = 0; i < strs.length; i++){//最外层的循环
            String tmp = strs[i];
            if(tmp.equals("0")){
                continue;
            }
            int[] map = new int[26];
            
            for(int j = 0; j < tmp.length(); j++){//得到最外层循环的tmp的map表
                map[tmp.charAt(j) - 'a']++;
            }

            List<String> listTmp = new ArrayList<>();
            listTmp.add(tmp);

            for(int k = i + 1; k < strs.length; k++){//内存循环，从i开始
                if(strs[k].equals("0")){
                    continue;
                }
                int[] mapClone = (int[])map.clone();
                int len = strs[k].length();
                if(len == tmp.length()){//至少得字符串长度相同才可能是异位词
                    int l = 0;
                    for(l = 0; l < len; l++){//开始检查是否是异位词
                        // mapClone[strs[k].charAt(l) - 'a']--;
                        // if(mapClone[strs[k].charAt(l) - 'a'] < 0){
                        //     break;
                        // }
                        
                        if(mapClone[strs[k].charAt(l) - 'a'] == 0){
                            break;
                        }
                        mapClone[strs[k].charAt(l) - 'a']--;

                    }
                    if(l != len){
                        continue;
                    }
                    //String newString = new String(strs[k].substring(0, strs[k].length()));
                    //String newString = strs[k];
                    listTmp.add(strs[k]);
                    strs[k] = "0";
                }
                else{
                    continue;
                }
            }
            res.add(listTmp);
            // strs[i] = "0";//其实这一步是多余的
        }
        return res;
    }
}

## 题解的思路是：找到一个可以代表 字母异位词 的 Key，然后构造 HashMap<Key, List<String>>，最后遍历HashMap拿到所有的value放入一个大的List中。
## 相较于我写的方法，由于题解用了HashMap，所以优化到了只需遍历一遍，速度大大提升。

## 题解1 以字典序排序后的String作为Key：

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}

## 题解2 由于互为字母异位词的两个字符串包含的字母相同，因此两个字符串中的相同字母出现的次数一定是相同的，故可以将每个字母出现的次数使用字符串表示，作为哈希表的键。

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
