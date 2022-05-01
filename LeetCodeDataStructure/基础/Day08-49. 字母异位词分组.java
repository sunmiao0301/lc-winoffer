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
## 也就是说 字母异位词的定位在于字母的字典序完全一致即可，（相当于一个 hashCode() )
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

## 二刷 重写了一版 但是结果一看就知道是 hashmap 使用的 equals 方法没有对于 int 数组进行针对性重写：所以我们判断 key 值的时候默认使用的 equals 会判断 int[]的地址是否相等 --> 由于都是new的，肯定不相等。
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<int[], List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for(int i = 0; i < strs.length; i++){
            int[] mapKey = new int[26];
            for(int j = 0; j < strs[i].length(); j++){
                mapKey[strs[i].charAt(j) - 'a']++;
            }
            // if(map.containsKey(mapKey)){
            //     map.get(mapKey).add()
            // }
            List<String> tmp = map.getOrDefault(mapKey, new ArrayList<String>());
            tmp.add(strs[i]);
            map.put(mapKey, tmp);
        }
        for (Map.Entry<int[], List<String>> p : map.entrySet()) {
        //p.getValue()
        res.add(p.getValue());
        }
        return res;
    }
}

## 所以我们用 

//数组转String
String mapKeyToStr = Arrays.toString(mapKey);
方法将 int[] 转为 String 再作为 Key，在hashmap中使用

执行结果：
通过
显示详情
添加备注

执行用时：
18 ms
, 在所有 Java 提交中击败了
11.59%
的用户
内存消耗：
45.5 MB
, 在所有 Java 提交中击败了
5.28%
的用户
通过测试用例：
115 / 115

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for(int i = 0; i < strs.length; i++){
            int[] mapKey = new int[26];
            for(int j = 0; j < strs[i].length(); j++){
                mapKey[strs[i].charAt(j) - 'a']++;
            }
            //数组转String
            String mapKeyToStr = Arrays.toString(mapKey);
            // if(map.containsKey(mapKey)){
            //     map.get(mapKey).add()
            // }
            List<String> tmp = map.getOrDefault(mapKeyToStr, new ArrayList<String>());
            tmp.add(strs[i]);
            map.put(mapKeyToStr, tmp);
        }
        for (Map.Entry<String, List<String>> p : map.entrySet()) {
        //p.getValue()
        res.add(p.getValue());
        }
        return res;
    }
}
