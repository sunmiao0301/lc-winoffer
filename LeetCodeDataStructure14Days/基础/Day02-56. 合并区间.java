## 第一版 原本的思路是先转存到LinkedList里面再按照数组的第一个数排序，然后继续操作，但是复杂度有点高了，换成直接对数组进行操作把。
class Solution {
    public int[][] merge(int[][] intervals) {
        //输入是二维数组
        //先根据第一位排序 然后遍历一遍即可合并 -- 如果合并成功，就合二为一继续和后面的遍历
    //五种可能
    //    [ ]
    //    [ ]

    //     [ ]
    //    [   ]

    //    [   ]
    //     [ ]
    
    //    [ ]
    //     [ ]

    //     [ ]
    //    [ ]
    LinkedList<Integer[][]> list = new LinkedList<>();
    for(int i = 0; i < intervals.length; i++){
        list.add()
    }
    return intervals;
    }
}

## 第二版 直接对数组进行操作
class Solution {
    public int[][] merge(int[][] intervals) {
        // return new int[][]{{1, 2}};
        for(int i = 0; i < intervals.length; i++){
            for(int j = i; j < intervals.length; j++){
                if(intervals[i][0] >= intervals[j][0]){
                    swap(i, j, intervals);
                }
            }
        }
        List<int[]> res = new LinkedList<>();
        for(int i = 1; i < intervals.length; i++){
            

            //intervals[i - 1][0] - intervals[i - 1][1] 前一个
            //intervals[i][0] - intervals[i][1] 后一个
            
            //如果interval[i][0] > interval[i - 1][1] 无重合
            //无重合 前一个直接入最终结果
            //反之，有重合 -- 取interval[i - 1][1]和interval[i][1]的较大值作为新的上届组成一个新区间
            //i++;
            if(intervals[i - 1][1] < intervals[i][0]){
                int[] tmp = new int[]{intervals[i - 1][0], intervals[i - 1][1]};
                res.add(tmp);
            }
            else{
                intervals[i][0] = intervals[i - 1][0];
                intervals[i][1] = Math.max(intervals[i - 1][1], intervals[i][1]);
            }
        }

            int[] last = new int[]{intervals[intervals.length - 1][0], intervals[intervals.length - 1][1]};
            if(res.size() < 1){
                    res.add(last);
                }
            else{
                int[] lastInList = res.get(res.size() - 1);
                if(lastInList[1] < last[0]){
                    res.add(last);
                }
                    // else{
                    //     res.remove(res.size() - 1);
                    //     int[] finall = new int[]{lastInList[0], intervals[i][1]};
                    //     res.add(finall);
                    // }
            }

        int[][] ret = new int[res.size()][2];
        for(int i = 0; i < res.size(); i++){
            ret[i][0] = (res.get(i))[0];
            ret[i][1] = (res.get(i))[1];
        }
        return ret;
    }

    public void swap(int a, int b, int[][] intervals){
        //数组只有一列 所以i~ j=0
        int tmpFir = intervals[a][0];
        int tmpSec = intervals[a][1];

        intervals[a][0] = intervals[b][0];
        intervals[a][1] = intervals[b][1];

        intervals[b][0] = tmpFir;
        intervals[b][1] = tmpSec;
    }
}
