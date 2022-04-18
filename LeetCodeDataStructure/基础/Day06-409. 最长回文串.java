## 第一版 如下 但是错在了如下样例上

执行结果：
解答错误
显示详情
添加备注

通过测试用例：
49 / 95
输入：
"civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth
查看全部
输出：
655
预期结果：
983

class Solution {
    public int longestPalindrome(String s) {
        //偶数直接加 奇数只能有一个 所以找一个最大的奇数
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        
        int max = 0;
        int res = 0;

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if((entry.getValue()) % 2 == 0){
                res += (entry.getValue());
            }
            else{
                max = (max < (entry.getValue())) ? (entry.getValue()) : max;
            }
		}
        return res + max;
    }
}

## 第二版

执行结果：
通过
显示详情
添加备注

执行用时：
8 ms
, 在所有 Java 提交中击败了
22.08%
的用户
内存消耗：
39.8 MB
, 在所有 Java 提交中击败了
23.17%
的用户
通过测试用例：
95 / 95

class Solution {
    public int longestPalindrome(String s) {
        //偶数直接加 奇数只能有一个 所以找一个最大的奇数
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        int res = 0;
        int existsOdd = 0;

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if((entry.getValue()) % 2 == 0){
                res += (entry.getValue());
            }
            else{
                res += (entry.getValue()) - 1;
                existsOdd++;
                // max = Math.max(entry.getValue(), max);
            }
		}
        return res + (existsOdd == 0 ? 0 : 1);
    }
}

## 第二版 由于字母种类有限 换哈希表为数组

执行结果：
通过
显示详情
添加备注

执行用时：
3 ms
, 在所有 Java 提交中击败了
40.64%
的用户
内存消耗：
39.1 MB
, 在所有 Java 提交中击败了
85.59%
的用户
通过测试用例：
95 / 95

class Solution {
    public int longestPalindrome(String s) {
        int[] map = new int[52];
        for(int i = 0; i < s.length(); i++){
            //ASCII中先大写字母65开始，后小写字母97开始
            //所以数组是AB...ab...
            if(s.charAt(i) >= 'a'){
                map[s.charAt(i) - 'a' + 26]++;
            }
            else{
                map[s.charAt(i) - 'A']++;
            }
        }
        int res = 0;
        int existsOdd = 0;
        for(int i = 0; i < 52; i++){
            if(map[i] % 2 == 0){
                res += map[i];
            }
            else{
                existsOdd++;
                res += map[i] - 1;
            }
        }
        return res + (existsOdd == 0 ? 0 : 1);
    }
}

## 题解 在字符编码方面，ASCII码为标准符号、数字、英文等进行了保留，取值范围是0～127，还有一部分作为扩展ASCII码128～255.
  
ASCII码直接128的话 会更快一点

执行结果：
通过
显示详情
添加备注

执行用时：
2 ms
, 在所有 Java 提交中击败了
61.33%
的用户
内存消耗：
39.2 MB
, 在所有 Java 提交中击败了
81.13%
的用户
通过测试用例：
95 / 95
  
class Solution {
    public int longestPalindrome(String s) {
        int[] count = new int[128];
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            char c = s.charAt(i);
            count[c]++;
        }

        int ans = 0;
        for (int v: count) {
            ans += v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }
}
  

