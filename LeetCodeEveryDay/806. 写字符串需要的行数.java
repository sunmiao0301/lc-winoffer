## 第一版 虽然时间一般 但是就是题解思路 -- 模拟

执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
17.08%
的用户
内存消耗：
39.3 MB
, 在所有 Java 提交中击败了
72.26%
的用户
通过测试用例：
27 / 27

class Solution {
    public int[] numberOfLines(int[] widths, String s) {
        //返回值是[a, b] a是多少行能放下S 以及最后一行使用的宽度是多少个单位？
        int lenInLine = 0;
        int lines = 0;
        // "bbbcccdddaa" 将会覆盖 9 * 10 + 2 * 4 = 98 个单位.
        // 最后一个字母 'a' 将会被写到第二行，因为第一行只剩下2个单位了。
        for(int i = 0; i < s.length(); i++){
            int index = s.charAt(i) - 'a';
            int len = widths[index];
            if(lenInLine + len > 100){
                lines++;
                lenInLine = len;
            }
            else{
                lenInLine += len;
            }
        }
        // int lines = len / 100 + 1;
        // int lastline = len % 100;
        return new int[]{lines + 1, lenInLine};
    }
}
