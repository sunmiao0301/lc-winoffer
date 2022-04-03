## 第一版
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
39.4 MB
, 在所有 Java 提交中击败了
7.00%
的用户
通过测试用例：
14 / 14
class Solution {
    public List<List<Integer>> generate(int numRows) {
        /**
        1
        1  1
        1  2  1
        */
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            List<Integer> tmp = new ArrayList();
            for(int j = 0; j <= i; j++){
                if(j == 0 || j == i){
                    tmp.add(1);
                }
                else{
                    tmp.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
                }
            }
            res.add(tmp);
        }
        return res;
    }
}
