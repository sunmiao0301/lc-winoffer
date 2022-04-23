## 第一版 通过

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
38.2 MB
, 在所有 Java 提交中击败了
74.87%
的用户
通过测试用例：
261 / 261

class Solution {
    public int binaryGap(int n) {
        //找到并返回 n 的二进制表示中两个 相邻 1 之间的 最长距离
        //如果只有 0 将两个 1 分隔开（可能不存在 0 ），则认为这两个 1 彼此 相邻 
        int res = 0;
        int tmp = 0;
        int flag = 0;
        while(n > 0){
            if((n & 1) == 1){
                res = Math.max(res, tmp);
                tmp = 1;
                flag = 1;
            }
            else{
                if(flag == 1)
                    tmp++;
            }
            n = n >>> 1;
        }
        return res;
    }
}
