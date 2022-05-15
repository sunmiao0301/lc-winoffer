## 递归回溯 第一版 --- 但是注意,我这样的写法无法通过""的测试样例

class Solution {
    Set<String> set = new HashSet<>();
    Map<Character, char[]> map = new HashMap<>();
    List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g' ,'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
        StringBuilder sb = new StringBuilder();
        helper(digits, sb);
    
        Iterator<String> it = set.iterator();  
        while (it.hasNext()) {
            res.add(it.next());
        }
        return res;
    }
    public void helper(String digits, StringBuilder sb){
        if("".equals(digits)){
            set.add(sb.toString());
            return;
        }
        char c = digits.charAt(0);
        char[] cArr = map.get(c);
        for(int j = 0; j < cArr.length; j++){
            sb.append(cArr[j]);
            helper(digits.substring(1, digits.length()), sb);//substring
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

## 第二版 额外加一个判断即可 -- 注意,还有第三版

执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
50.33%
的用户
内存消耗：
39.9 MB
, 在所有 Java 提交中击败了
62.85%
的用户
通过测试用例：
25 / 25

class Solution {
    Set<String> set = new HashSet<>();
    Map<Character, char[]> map = new HashMap<>();
    List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if("".equals(digits)){
            return res;
        }
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g' ,'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
        StringBuilder sb = new StringBuilder();
        helper(digits, sb);
    
        Iterator<String> it = set.iterator();  
        while (it.hasNext()) {
            res.add(it.next());
        }
        return res;
    }
    public void helper(String digits, StringBuilder sb){
        if("".equals(digits)){
            set.add(sb.toString());
            return;
        }
        char c = digits.charAt(0);
        char[] cArr = map.get(c);
        for(int j = 0; j < cArr.length; j++){
            sb.append(cArr[j]);
            helper(digits.substring(1, digits.length()), sb);//substring
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

## 题解 没用到set

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<String>();
        if (digits.length() == 0) {
            return combinations;
        }
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
        if (index == digits.length()) {
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap, digits, index + 1, combination);
                combination.deleteCharAt(index);
            }
        }
    }
}

## 第三版 我想了一下 确实不需要set,直接往res(ArrayList)里面塞就行了 完美

执行结果：
通过
显示详情
添加备注

执行用时：
0 ms
, 在所有 Java 提交中击败了
100.00%
的用户
内存消耗：
39.8 MB
, 在所有 Java 提交中击败了
71.03%
的用户
通过测试用例：
25 / 25

class Solution {
    // Set<String> set = new HashSet<>();
    Map<Character, char[]> map = new HashMap<>();
    List<String> res = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if("".equals(digits)){
            return res;
        }
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g' ,'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
        StringBuilder sb = new StringBuilder();
        helper(digits, sb);
    
        // Iterator<String> it = set.iterator();  
        // while (it.hasNext()) {
        //     res.add(it.next());
        // }
        return res;
    }
    public void helper(String digits, StringBuilder sb){
        if("".equals(digits)){
            res.add(sb.toString());
            return;
        }
        char c = digits.charAt(0);
        char[] cArr = map.get(c);
        for(int j = 0; j < cArr.length; j++){
            sb.append(cArr[j]);
            helper(digits.substring(1, digits.length()), sb);//substring
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
