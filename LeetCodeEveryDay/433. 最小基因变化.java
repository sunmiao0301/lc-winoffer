## 第一版 错误在如下样例上：

执行结果：
执行出错
显示详情
添加备注

java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because "<local9>" is null
  at line 50, Solution.minMutation
  at line 54, __DriverSolution__.__helper__
  at line 90, __Driver__.main
最后执行的输入：
"AACCTTGG"
"AATTCCGG"
["AATTCCGG","AACCTGGG","AACCCCGG","AACCTACC"]


class Solution {
    public int minMutation(String start, String end, String[] bank) {
        //一次基因变化就意味着这个基因序列中的一个字符发生了变化。
        //注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
        //start.length == 8 && end.length == 8(所以与趋势科技笔试题还是有点差距的)
        //0 <= bank.length <= 10
        //请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。

        //由于测试样例很少 所以我们稍微粗暴点
        Map<Integer, List<String>> map = new HashMap<>();

        //首先统计和start不一样的位数，放入map
        boolean flagOfNoEnd = true;
        for(int i = 0; i < bank.length; i++){
            String tmp = bank[i];
            int key = 0;
            for(int j = 0; j < 8; j++){
                if(start.charAt(j) != tmp.charAt(j)){
                    key++;
                }
            }
            if(tmp.equals(end)){
                flagOfNoEnd = false;
                List<String> list = new LinkedList<String>();
                list.add(tmp);
                map.put(key, list);
                break;
            }
            List<String> list = map.getOrDefault(key, new LinkedList<String>());
            list.add(bank[i]);
            map.put(key, list);
        }

        //预判断一下end，没有直接-1
        if(flagOfNoEnd)
            return -1;

        //得到end与start相差的位数
        int diffFromStartAndEnd = 0;
        for(int i = 0; i < 8; i++){
            if(start.charAt(i) != end.charAt(i)){
                diffFromStartAndEnd++;
            }
        }
        //进行排除 -- 可以写一个判断两个字符串是否只差一位的函数
        for(int i = 1; i < diffFromStartAndEnd; i++){
            List<String> pre = map.get(i);
            List<String> next = map.get(i + 1);

            Iterator<String> iterator = next.iterator();
            while (iterator.hasNext()) {
                String str = iterator.next();
                int j = 0;
                for(j = 0; j < pre.size(); j++){
                    if(diffInOneChar(str, pre.get(j))) break;
                }
                if(j == pre.size())
                    iterator.remove();//使用迭代器的删除方法删除
            }
        }
        if(map.get(diffFromStartAndEnd) == null){
            return -1;
        }
        return diffFromStartAndEnd;
        
    }
    public boolean diffInOneChar(String a, String b){
        int diffNum = 0;
        for(int i = 0; i < 8; i++){
            if(a.charAt(i) != b.charAt(i)){
                diffNum++;
            }
        }
        if(diffNum == 1){
            return true;
        }
        return false;
    }
}

## 第二版 针对第一版出现的问题 我将其修改如下
## 这个错误的样例使我认识到我的思路是错误的。

## 我的主要思路是这样的：
1）统计 bank 中各个基因和 start 不一样的位数，放入 map
2）通过 start 和 end 基因，得到不同的基因位数 diffFromStartAndEnd
3）进行排除，按照 key 从 1 递增，依次确认， （写一个判断两个字符串是否只差一位的函数）

但是这一版遇到的错误样例让我发现，我的思路不对，因为基因从 start ---> end 不一定需要基因不同位数必须递增，比如从 "AACCGGTT" 到 "AAACGGTA" 不一定是两步就可以完成
而是根据 bank 的情况，可能需要四步才能完成。

执行结果：
解答错误
显示详情
添加备注

通过测试用例：
11 / 14
输入：
"AACCGGTT"
"AAACGGTA"
["AACCGATT","AACCGATA","AAACGATA","AAACGGTA"]
输出：
2
预期结果：
4

class Solution {
    public int minMutation(String start, String end, String[] bank) {
        //一次基因变化就意味着这个基因序列中的一个字符发生了变化。
        //注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
        //start.length == 8 && end.length == 8(所以与趋势科技笔试题还是有点差距的)
        //0 <= bank.length <= 10
        //请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。

        //由于测试样例很少 所以我们稍微粗暴点
        Map<Integer, List<String>> map = new HashMap<>();

        //首先统计和start不一样的位数，放入map
        boolean flagOfNoEnd = true;
        for(int i = 0; i < bank.length; i++){
            String tmp = bank[i];
            int key = 0;
            for(int j = 0; j < 8; j++){
                if(start.charAt(j) != tmp.charAt(j)){
                    key++;
                }
            }
            if(tmp.equals(end)){
                flagOfNoEnd = false;
                List<String> list = new LinkedList<String>();
                list.add(tmp);
                map.put(key, list);
                break;
            }
            List<String> list = map.getOrDefault(key, new LinkedList<String>());
            list.add(bank[i]);
            map.put(key, list);
        }

        //预判断一下end，没有直接-1
        if(flagOfNoEnd)
            return -1;

        //得到end与start相差的位数
        int diffFromStartAndEnd = 0;
        for(int i = 0; i < 8; i++){
            if(start.charAt(i) != end.charAt(i)){
                diffFromStartAndEnd++;
            }
        }
        //进行排除 -- 可以写一个判断两个字符串是否只差一位的函数
        for(int i = 1; i < diffFromStartAndEnd; i++){
            List<String> pre = map.get(i);
            List<String> next = map.get(i + 1);

            if(pre == null || next == null) return -1;

            Iterator<String> iterator = next.iterator();
            while (iterator.hasNext()) {
                String str = iterator.next();
                int j = 0;
                for(j = 0; j < pre.size(); j++){
                    if(diffInOneChar(str, pre.get(j))) break;
                }
                if(j == pre.size())
                    iterator.remove();//使用迭代器的删除方法删除
            }
        }
        if(map.get(diffFromStartAndEnd) == null){
            return -1;
        }
        return diffFromStartAndEnd;
        
    }
    public boolean diffInOneChar(String a, String b){
        int diffNum = 0;
        for(int i = 0; i < 8; i++){
            if(a.charAt(i) != b.charAt(i)){
                diffNum++;
            }
        }
        if(diffNum == 1){
            return true;
        }
        return false;
    }
}

p.s. 虽然以上写的不多，但是学到了一个对LinkedList的 一边遍历、一边删除的相关知识"https://blog.csdn.net/github_2011/article/details/54927531
for-each不行，for + size() 更不行，用 迭代器 + remove() 才对，此外还需注意迭代器删完之后是 null 而不是 size() == 0
