## 第一版 通过 思路和题解完全一致

class Solution {
    public List<Integer> partitionLabels(String s) {
        //尽可能多的片段
        //同一字母最多出现在一个片段中
        int[] arr = new int[26];
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            arr[s.charAt(i) - 'a'] = i;
        }

        int start = 0;
        int max = 0;
        int i = 0;
        while(i < s.length()){
            max = arr[s.charAt(i) - 'a'];
            while(i <= max){
                if(arr[s.charAt(i) - 'a'] > max){
                    max = arr[s.charAt(i) - 'a'];
                }
                else{
                    i++;
                }
            }
            res.add(max - start + 1);
            start = i;
        }
        
        return res;
    }
}
