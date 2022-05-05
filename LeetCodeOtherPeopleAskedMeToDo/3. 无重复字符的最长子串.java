## 第一版 也做了很久 滑动窗口双指针题确实不好写 外加本题有一个容易忽略的易错点，如下：

                if(left < map.get(tmp) + 1){
                    left = map.get(tmp) + 1;//left 置为上一个的位置
                }
                
此外，滑动窗口可以总结一下，形成一套方法论：（窗口左边界为left,窗口右边界为right,那么包含在其中的就是 左闭右闭区间内的值 [left, right]
                
执行结果：
通过
显示详情
添加备注

执行用时：
4 ms
, 在所有 Java 提交中击败了
86.49%
的用户
内存消耗：
41.5 MB
, 在所有 Java 提交中击败了
43.60%
的用户
通过测试用例：
987 / 987


class Solution {
    public int lengthOfLongestSubstring(String s) {
        //连续 ---> 滑动窗口
        //0 <= s.length <= 5 * 104
        if(s.length() == 0 || s.length() == 1) return s.length();

        Map<Character, Integer> map = new HashMap<>();
        //map.put(s.charAt(0), 0);

        int left = 0, right = 0;
        int len = s.length();
        int res = 1;

        while(right < len){
            char tmp = s.charAt(right);
            
            if(map.containsKey(tmp)){

                res = Math.max(res, right - left);
                //这里很容易忽略，需要想清楚。
                if(left < map.get(tmp) + 1){
                    left = map.get(tmp) + 1;//left 置为上一个的位置
                }
            }
            else{
                res = Math.max(res, right - left + 1);
            }
            map.put(tmp, right);
            right++;

        }

        return Math.max(res, right - left);// res;
    }
}
