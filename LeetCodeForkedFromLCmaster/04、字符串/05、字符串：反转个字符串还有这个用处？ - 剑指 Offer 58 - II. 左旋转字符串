2nd
换成StringBuilder之后速度快多了
class Solution {
    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            sb.append(s.charAt((i + n) % s.length()));
        }
        return sb.toString();
    }
}

//第一版 常考题 难度低 左旋字符串——用%

这种方法虽然效率低 但是是不借助各种库函数的方法

class Solution {
    public String reverseLeftWords(String s, int n) {
        String res = "";
        for(int i = n; i < n + s.length(); i++)
            res += s.charAt(i % s.length());
        return res;
    }
}
