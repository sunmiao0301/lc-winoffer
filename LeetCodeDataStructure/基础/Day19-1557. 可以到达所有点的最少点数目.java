## 第一版 
## 找到最小的点集使得从这些点出发能到达图中所有点。题目保证解存在且唯一。
## 我有一个思路是，不断求包含关系，最后最小的包含关系中。。好像也想不到后面该怎么做了

## 题解 ———— 只需要返回入度为零的节点

解释
"由于给定的图是有向无环图，基于上述分析可知，对于任意入度不为零的节点 xx，一定存在另一个节点 zz，使得从节点 zz 出发可以到达节点 xx。为了获得最小的点集，只有入度为零的节点才应该加入最小的点集。
"由于入度为零的节点必须从其自身出发才能到达该节点，从别的节点出发都无法到达该节点，因此最小的点集必须包含所有入度为零的节点。
"因为入度不为零的点总可以由某个入度为零的点到达，所以这些点不包括在最小的合法点集当中。
"因此，我们得到「最小的点集使得从这些点出发能到达图中所有点」就是入度为零的所有点的集合。

执行结果：
通过
显示详情
添加备注

执行用时：
9 ms
, 在所有 Java 提交中击败了
86.22%
的用户
内存消耗：
88.1 MB
, 在所有 Java 提交中击败了
9.39%
的用户
通过测试用例：
66 / 66

class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean[] exclude = new boolean[n];//默认值是false
        for(int i = 0; i < edges.size(); i++){
            int resExclude = edges.get(i).get(1);
            exclude[resExclude] = true;
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < exclude.length; i++){
            if(!exclude[i]){
                res.add(i);
            }
        }
        return res;
    }
}
