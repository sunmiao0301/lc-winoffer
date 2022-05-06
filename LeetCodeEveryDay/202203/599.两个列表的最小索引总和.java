## 第一版 但是复杂度不是最优
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < list1.length; i++){
            map.put(list1[i], i);
        }
        LinkedList<String> res = new LinkedList<>();
        int minIndex = list1.length + list2.length - 2;
        for(int i = 0; i < list2.length; i++){
            if(map.containsKey(list2[i])){
                if(map.get(list2[i]) + i == minIndex){
                    res.add(list2[i]);
                }
                else if(map.get(list2[i]) + i < minIndex){
                    res = new LinkedList<>();
                    res.add(list2[i]);
                    minIndex = map.get(list2[i]) + i;
                }
            }
        }
        String[] ret = new String[res.size()];
        for(int i = 0; i < ret.length; i++){
            ret[i] = res.get(i);
        }
        return ret;
    }
}

## 优化后的第二版 近100% 思路已经是标准题解了
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
        for(int i = 0; i < list1.length; i++){
            map.put(list1[i], i);
        }
        List<String> res = new ArrayList<>(); //将LinkedList换成ArrayList会好些
        int minIndex = list1.length + list2.length - 2;
        for(int i = 0; i < list2.length; i++){
            if(map.containsKey(list2[i])){
                if(i > minIndex) // 如果单个索引已经大于minIndex了，就不必继续了，直接break
                    break;
                if(map.get(list2[i]) + i == minIndex){
                    res.add(list2[i]);
                }
                else if(map.get(list2[i]) + i < minIndex){
                    //res = new LinkedList<>();
                    res.clear(); // 用clear()方法也比直接新建一个，然后更换引用，会更快一些
                    res.add(list2[i]);
                    minIndex = map.get(list2[i]) + i;
                }
            }
        }
        
        return res.toArray(new String[res.size()]);
        
        // String[] ret = new String[res.size()];
        // for(int i = 0; i < ret.length; i++){
        //     ret[i] = res.get(i);
        // }
        // return ret;
    }
}
