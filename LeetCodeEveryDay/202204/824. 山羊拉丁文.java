## 第一版 但是没通过以下样例 因为包括大写字母 然后第二版即通过

执行结果：
解答错误
显示详情
添加备注

通过测试用例：
69 / 99
输入：
"Each word consists of lowercase and uppercase letters only"
输出：
"achEmaa ordwmaaa onsistscmaaaa ofmaaaaa owercaselmaaaaaa andmaaaaaaa uppercasemaaaaaaaa etterslmaaaaaaaaa onlymaaaaaaaaaa"
预期结果：
"Eachmaa ordwmaaa onsistscmaaaa ofmaaaaa owercaselmaaaaaa andmaaaaaaa uppercasemaaaaaaaa etterslmaaaaaaaaa onlymaaaaaaaaaa"

## 第二版 通过

执行结果：
通过
显示详情
添加备注

执行用时：
2 ms
, 在所有 Java 提交中击败了
80.74%
的用户
内存消耗：
39.5 MB
, 在所有 Java 提交中击败了
73.09%
的用户
通过测试用例：
99 / 99

class Solution {
    public String toGoatLatin(String sentence) {
        StringBuilder sb = new StringBuilder();
        StringBuilder res = new StringBuilder();
        int len = sentence.length();
        int wordInSentence = 1;
        for(int i = 0; i < len; i++){
            //sentence 由英文字母和空格组成 所有单词由单个空格分隔
            //sentence 不含前导或尾随空格
            if(sentence.charAt(i) == ' '){
                // String tmp = sb.toString();
                if(sb.charAt(0) == 'a' || sb.charAt(0) == 'e' || sb.charAt(0) == 'i' || sb.charAt(0) == 'o' || sb.charAt(0) == 'u' || sb.charAt(0) == 'A' || sb.charAt(0) == 'E' || sb.charAt(0) == 'I' || sb.charAt(0) == 'O' || sb.charAt(0) == 'U'){
                    sb.append("ma");
                }
                else{
                    char first = sb.charAt(0);
                    sb.delete(0, 1);
                    sb.append(first);
                    sb.append("ma");
                }
                for(int j = wordInSentence; j > 0; j--){
                    sb.append('a');
                }
                wordInSentence++;
                res.append(sb + " ");
                sb.delete(0, sb.length());
            }
            else{
                sb.append(sentence.charAt(i));
            }
        }

        if(sb.charAt(0) == 'a' || sb.charAt(0) == 'e' || sb.charAt(0) == 'i' || sb.charAt(0) == 'o' || sb.charAt(0) == 'u' || sb.charAt(0) == 'A' || sb.charAt(0) == 'E' || sb.charAt(0) == 'I' || sb.charAt(0) == 'O' || sb.charAt(0) == 'U'){
            sb.append("ma");
        }
        else{
            char first = sb.charAt(0);
            sb.delete(0, 1);
            sb.append(first);
            sb.append("ma");
        }
        for(int j = wordInSentence; j > 0; j--){
            sb.append('a');
        }
        res.append(sb);


        return res.toString();
    }
}
