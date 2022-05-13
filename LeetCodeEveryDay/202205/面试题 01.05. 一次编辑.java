## 第一版 通过 和题解思路完全一致

当 n - m = 1 或 m - n = 1 时，由于两种情况具有对称性，因此可以定义一个函数统一计算这两种情况。
用 longer 表示较长的字符串，shorter 表示较短的字符串。
同时遍历两个字符串，比较对应下标处的字符是否相同，如果字符相同则将两个字符串的下标同时加 1，如果字符不同则只将 longer 的下标加 1。
遍历过程中如果出现两个字符串的下标之差大于 1 则不符合一次编辑，遍历结束时如果两个字符串的下标之差不大于 1 则符合一次编辑。

当 m = n 时，同时遍历 first 和 second，比较相同下标处的字符是否相同。如果字符不同的下标个数不超过 1，则符合一次编辑或零次编辑。

执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
97.69%
的用户
内存消耗：
41.7 MB
, 在所有 Java 提交中击败了
5.53%
的用户
通过测试用例：
1146 / 1146

class Solution {
    public boolean oneEditAway(String first, String second) {
        //插入、删除、替换、直接相等 --- a=b插入 实际上 等于 b=a删除 --- 所以直接合并为一个函数就行了
        if(Math.abs(first.length() - second.length()) > 1)
            return false;
        if(first.equals(second) || needInsertOrDelete(second, first)|| needSwap(first, second))
            return true;
        return false;
    }
    public boolean needInsertOrDelete(String first, String second){
        if(first.length() == second.length()) return false;
        String shorter = (first.length() > second.length()) ? second : first;
        String longer = (first.length() < second.length()) ? second : first;

        int numberOfDiff = 0;
        for(int iS = 0, iL = 0; iS < shorter.length(); iS++, iL++){
            if(shorter.charAt(iS) != longer.charAt(iL) && numberOfDiff == 0){
                // iL++;
                iS--;
                numberOfDiff++;
            }
            else if(shorter.charAt(iS) != longer.charAt(iL) && numberOfDiff != 0){
                numberOfDiff++;
                break;
            }
        }
        if(numberOfDiff <= 1){//有可能是"abcde"和"abcdee"的情况。
            return true;
        }
        return false;
    }

    public boolean needSwap(String first, String second){
        if(first.length() != second.length()) return false;
        int numberOfDiff = 0;
        for(int iS = 0, iL = 0; iS < first.length(); iS++, iL++){
            if(first.charAt(iS) != second.charAt(iL) && numberOfDiff == 0){
                numberOfDiff++;
            }
            else if(first.charAt(iS) != second.charAt(iL) && numberOfDiff != 0){
                numberOfDiff++;
                break;
            }
        }
        if(numberOfDiff == 1){
            return true;
        }
        return false;
    }
}
