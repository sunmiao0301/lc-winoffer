## 第一版 要考虑的情况蛮多的

class Solution {
    public int lengthLongestPath(String input) {
        //'\n' 和 '\t' 分别是换行符和制表符。
        //每个目录名由字母、数字和/或空格组成，每个文件名遵循 name.extension 的格式，其中 name 和 extension由字母、数字和/或空格组成 -- 文件名后缀也有可能包含\
        //遍历两遍吧，稍慢一点，但是可读性高
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == '\\'){
                list.add(sb.toString());
                sb.remove(0, sb.length());
                sb.append('\\');
            }
            else{
                sb.append(input.charAt(i));
            }
        }
        
        Stack<String> stack = new Stack<>();
        int max = 0;
        //每一步入栈之后 都再次统计一下max


    }
}

