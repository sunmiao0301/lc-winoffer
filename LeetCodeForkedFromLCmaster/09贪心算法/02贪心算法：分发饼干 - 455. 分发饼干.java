## 第一版 一遍过
执行结果：
通过
显示详情
添加备注

执行用时：
7 ms
, 在所有 Java 提交中击败了
99.94%
的用户
内存消耗：
42.6 MB
, 在所有 Java 提交中击败了
11.01%
的用户
通过测试用例：
21 / 21
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int gIndex = g.length - 1;
        int sIndex = s.length - 1;
        while(sIndex >= 0 && gIndex >= 0){
            if(s[sIndex] >= g[gIndex]){
                res++;
                sIndex--;
                gIndex--;
            }
            else{
                gIndex--;
            }
        }
        return res;
    }
}
