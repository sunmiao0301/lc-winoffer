## 第一版 100% 算不算高效的算法呢？

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
41 MB
, 在所有 Java 提交中击败了
43.74%
的用户
通过测试用例：
133 / 133

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        //先找行 后找列
        //何为高效的算法？
        //1 <= m, n <= 100
        int i = 0;
        for(i = 0; i < matrix.length - 1; i++){
            //if(i + 1 > matrix.length)
            if(matrix[i][0] <= target && matrix[i + 1][0] > target){
                break;
            }
        }
        //if(i == matrix.length - 1 && matrix[i][matrix[0].length - 1] < target) return false;
        for(int j = 0; j < matrix[0].length; j++){
            if(matrix[i][j] == target){
                return true;
            }
        }
        return false;
    }
}

## 题解的算法是二分查找 

包括先行二分，然后列二分 和 行列合并为一行，然后二分 两种方法 "https://leetcode-cn.com/problems/search-a-2d-matrix/solution/sou-suo-er-wei-ju-zhen-by-leetcode-solut-vxui/
