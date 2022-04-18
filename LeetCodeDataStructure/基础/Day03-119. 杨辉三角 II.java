## 第一版 应该是我能写出的最简方法了

执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
75.89%
的用户
内存消耗：
39.1 MB
, 在所有 Java 提交中击败了
35.24%
的用户
通过测试用例：
34 / 34

class Solution {
    public List<Integer> getRow(int rowIndex) {
        //根据测试用例 最上面一行是第0行
        //第0行和第1行可以直接写出
        List<Integer> res = new LinkedList<>();
        res.add(1);
        if(rowIndex == 0) return res;
        res.add(1);
        for(int i = 1; i < rowIndex; i++){
            int size = res.size();
            res.add(1);
            for(int j = 1; j < size; j++){
                res.add(res.get(j) + res.get(j - 1));
            }
            while(size > 0){
                res.remove(0);
                size--;
            }
            res.add(1);
        }
        return res;
    }
}

## 题解 实际上不需要remove了，只需要不断的加就行了

进一步优化

能否只用一个数组呢？

当前行第 i 项的计算只与上一行第 i−1 项及第 i 项有关。

因此我们可以倒着计算当前行，这样计算到第 i 项时，第 i−1 项仍然是上一行的值。-- 注意 是倒着！！

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add(0);
            for (int j = i; j > 0; --j) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
        }
        return row;
    }
}

复杂度分析

时间复杂度：O(rowIndex^2)
空间复杂度：O(1)。不考虑返回值的空间占用。

## 题解 还有最简单的方法 但是需要推导 -- 线性递归
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        row.add(1);
        for (int i = 1; i <= rowIndex; ++i) {
            row.add((int) ((long) row.get(i - 1) * (rowIndex - i + 1) / i));
        }
        return row;
    }
}

复杂度分析

时间复杂度：O(rowIndex)

空间复杂度：O(1)。不考虑返回值的空间占用。
