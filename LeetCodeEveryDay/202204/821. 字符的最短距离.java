## 第一版 想到了思路 但是觉得不够优美 

执行结果：
通过
显示详情
添加备注

执行用时：
2 ms
, 在所有 Java 提交中击败了
40.46%
的用户
内存消耗：
41.5 MB
, 在所有 Java 提交中击败了
28.30%
的用户
通过测试用例：
76 / 76

class Solution {
    public int[] shortestToChar(String s, char c) {
        //首先找到所有的c 然后找到两个c和下标 当i = 后一个c的下标的时候 开始找下一个c
        List<Integer> list = new ArrayList<>();
        int[] res = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == c){
                list.add(i);
            }
        }
        int low = -(int)Math.pow(10, 4);
        int a = low;
        int b = low;
        int j = 0;
        for(int i = 0; i < s.length(); i++){
            //题目数据保证 c 在 s 中至少出现一次
            if(i >= Math.max(a, b) || a == low || b == low){
                while(j < list.size()){
                    if(a <= b){
                        a = list.get(j);
                    }
                    else{
                        b = list.get(j);
                    }
                    j++;
                    break;
                }
            }
            res[i] = Math.min(Math.abs(a - i), Math.abs(b - i));
        }
        return res;
    }
}

## 题解 还是题解聪明 
我的做法是一边遍历一边找当前i位置下的字符离左右最近的c的位置并且比较大小，
但是题解是两次遍历，
一次从左往右，找到每个值离最近的左边的c的位置，（这一趟不管右边的c的情况）
第二次从右往左，同理

class Solution {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];

        for (int i = 0, idx = -n; i < n; ++i) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = i - idx;
        }

        for (int i = n - 1, idx = 2 * n; i >= 0; --i) {
            if (s.charAt(i) == c) {
                idx = i;
            }
            ans[i] = Math.min(ans[i], idx - i);
        }
        return ans;
    }
}

时间复杂度：O(n)，其中 n 是字符串 s 的长度。

空间复杂度：O(1)。返回值不计入空间复杂度。
