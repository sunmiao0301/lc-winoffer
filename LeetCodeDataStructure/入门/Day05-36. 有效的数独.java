## 第一版 自己完成 暴力了
class Solution {
    public boolean isValidSudoku(char[][] board) {
        //只需要根据以上规则，验证已经填入的数字是否有效即可
        //空白格用 '.' 表示。
        //一定是9*9数独 难度就下来了
        HashSet<Character> set = new HashSet<>();
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[i][j] == '.'){
                    continue;
                }
                else{
                    if(!set.add(board[i][j])){
                        return false;
                    }
                }
            }
            set.clear();
        }

        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(board[j][i] == '.'){
                    continue;
                }
                else{
                    if(!set.add(board[j][i])){
                        return false;
                    }
                }
            }
            set.clear();
        }

        for(int i = 0; i < 9; i = i + 3){
            for(int j = 0; j < 9; j = j + 3){
                for(int n = i; n < i + 3; n++){
                    for(int m = j; m < j + 3; m++){
                if(board[n][m] == '.'){
                    continue;
                }
                else{
                    if(!set.add(board[n][m])){
                        return false;
                    }
                }
                    }
                }
                set.clear();
            }
        }
        return true;
    }
}

## 题解 空间换时间 （比如一共 9 + 9 + 9 个需要验证的组合，那么就可以建立 27 个哈希表来验证，同时由于只有九个数，我们可以将哈希表用数组代替，取得更好的效果）
## 更好的想法是：
