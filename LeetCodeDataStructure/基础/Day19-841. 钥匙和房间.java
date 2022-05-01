## 第一版 效率一般 感觉把 set 换成 int[] 会好很多

执行结果：
通过
显示详情
添加备注

执行用时：
2 ms
, 在所有 Java 提交中击败了
32.94%
的用户
内存消耗：
41.1 MB
, 在所有 Java 提交中击败了
42.70%
的用户
通过测试用例：
68 / 68

class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        //最初，除 0 号房间外的其余所有房间都被锁住。
        //房间里可能有相同之前已经有了的钥匙

        Queue<List<Integer>> queue = new LinkedList<>();
        //已经打开的房间号是用set还是int[]存储捏？ -- 都试试吧
        Set<Integer> set = new HashSet<>();
        set.add(0);
        queue.offer(rooms.get(0));

        while(!queue.isEmpty()){
            List<Integer> keys = queue.poll();
            for(int i = 0; i < keys.size(); i++){
                if(!set.contains(keys.get(i))){
                    set.add(keys.get(i));
                    queue.offer(rooms.get(keys.get(i)));
                }
            }
        }
        if(set.size() == rooms.size()){
            return true;
        }
        return false;
    }
}

## 第二版 改成int[]确实好不少 但是还是没 100%
  
执行结果：
通过
显示详情
添加备注

执行用时：
1 ms
, 在所有 Java 提交中击败了
61.74%
的用户
内存消耗：
41.1 MB
, 在所有 Java 提交中击败了
46.44%
的用户
通过测试用例：
68 / 68
  
class Solution {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        //最初，除 0 号房间外的其余所有房间都被锁住。
        //房间里可能有相同之前已经有了的钥匙

        Queue<List<Integer>> queue = new LinkedList<>();
        //已经打开的房间号是用set还是int[]存储捏？ -- 都试试吧
        int[] roomsOpened = new int[rooms.size()];
        roomsOpened[0]++;
        queue.offer(rooms.get(0));

        while(!queue.isEmpty()){
            List<Integer> keys = queue.poll();
            for(int i = 0; i < keys.size(); i++){
                if(roomsOpened[keys.get(i)] != 1){
                    roomsOpened[keys.get(i)]++;
                    queue.offer(rooms.get(keys.get(i)));
                }
            }
        }
        for(int i = 0; i < roomsOpened.length; i++){
            if(roomsOpened[i] == 0){
                return false;
            }
        }
        return true;
    }
}

## 题解 虽然思路一样 但是由于一些小的优化（比如 int[] ---> boolean[] 之类）效果好了很多
class Solution {
    boolean[] vis;
    int num;

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        num = 0;
        vis = new boolean[n];
        dfs(rooms, 0);
        return num == n;
    }

    public void dfs(List<List<Integer>> rooms, int x) {
        vis[x] = true;
        num++;
        for (int it : rooms.get(x)) {
            if (!vis[it]) {
                dfs(rooms, it);
            }
        }
    }
}
