//第一版 一遍过 但是效率一般
难度不大 唯一需要注意的是 刚开始写就得确定自己要用的是开区间还是闭区间！（这里我选择的是闭区间）

因为我对字符串中的字符的处理还不熟练 
用的是String 转 char[] 和 char[] 转 String 
方法如下：
1.
String类的toCharArray()方法，将字符串转为字符(char)数组
String ss=”abc”;
char[] cc;
cc=ss.toCharArray();
这时cc={‘a’,’b’,’c’}；
2.
String类的valueOf()方法，将字符(char)数组转换为字符串
char[] cc={‘a’,’b’,’c’};
ss=String.valueOf(cc);
这时ss=“abc”；

class Solution {
    public String reverseStr(String s, int k) {
        char[] c;
        c = s.toCharArray();
        int i = 0;//左右都是闭区间
        while(i < s.length()){
            if(i + 2 * k - 1 <= s.length() - 1){
                int l = i;
                int r = i + k - 1; 
                while(l < r){
                    char temp = c[l];
                    c[l] = c[r];
                    c[r] = temp;
                    l++;
                    r--;
                }
                //reverse(c, i, i + k - 1);            
                i = i + 2 * k;
            }
            else if(i + k > s.length()){
                int l = i;
                int r = s.length() - 1;
                while(l < r){
                    char temp = c[l];
                    c[l] = c[r];
                    c[r] = temp;
                    l++;
                    r--;
                }
                //reverse(c, i, s.length() - 1);
                break;
            }
            else{//if(i + 2 * k > s.length() && i + k < s.length())
                int l = i;
                int r = i + k - 1;
                while(l < r){
                    char temp = c[l];
                    c[l] = c[r];
                    c[r] = temp;
                    l++;
                    r--;
                }
                //reverse(s, i, i + k - 1);
                break;
            }
        }
        s = String.valueOf(c);
        return s;
    }
}
/*
    void reverse(char[] c, int l, int r){
        while(l < r){
            char temp = c[l];
            c[l] = c[r];
            c[r] = temp;
            l++;
            r--;
        }
    }
*/

//第二版 函数一下
执行结果：
通过
执行用时：
1 ms
, 在所有 Java 提交中击败了
68.68%
的用户
内存消耗：
38.3 MB
, 在所有 Java 提交中击败了
76.51%
的用户

这里有一个问题：
为什么有时候函数可以对局部变量操作，有时候不能，而只能对全局变量操作？

class Solution {
    public String reverseStr(String s, int k) {
        char[] c;
        c = s.toCharArray();
        int i = 0;//左右都是闭区间
        while(i < s.length()){
            if(i + 2 * k - 1 <= s.length() - 1){
                reverse(c, i, i + k - 1);            
                i = i + 2 * k;
            }
            else if(i + k > s.length()){
                reverse(c, i, s.length() - 1);
                break;
            }
            else{//if(i + 2 * k > s.length() && i + k < s.length())
                reverse(c, i, i + k - 1);
                break;
            }
        }
        s = String.valueOf(c);
        return s;
    }
    void reverse(char[] c, int l, int r){
        while(l < r){
            char temp = c[l];
            c[l] = c[r];
            c[r] = temp;
            l++;
            r--;
        }
    }
}

//第三版 for循环中用 i = i + 2 * k 而不是 i++
大大减少了代码行数
class Solution {
    public String reverseStr(String s, int k) {
        char[] c = s.toCharArray();
        int i;
        for(i = 0; i < s.length(); i += 2 * k){//用闭区间
            int l = i;
            int r = i + k - 1 < s.length() ? i + k - 1 : s.length() - 1;
            while(l < r){
                char temp = c[l];
                c[l] = c[r];
                c[r] = temp;                    
                l++;
                r--;
                }
            /*
            if(i + k - 1 < s.length()){
                int l = i;
                int r = i + k - 1;
                while(l < r){
                    char temp = c[l];
                    c[l] = c[r];
                    c[r] = temp;
                    l++;
                    r--;
                }
            }
            else{
                int l = i;
                int r = s.length() - 1;
                while(l < r){
                    char temp = c[l];
                    c[l] = c[r];
                    c[r] = temp;
                    l++;
                    r--;
                }
            }
            */
        }
        return String.valueOf(c);
    }
}

//标准题解
class Solution {
    public String reverseStr(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        return new String(arr);
    }

    public void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
