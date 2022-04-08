## 第一版 一遍过 但是效率没最好 -- 但是题解也就这个思路
执行结果：
通过
显示详情
添加备注

执行用时：
3 ms
, 在所有 Java 提交中击败了
75.11%
的用户
内存消耗：
42.1 MB
, 在所有 Java 提交中击败了
64.65%
的用户
通过测试用例：
38 / 38
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        //树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）?
        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        if(root == null) return res;
        q.offer(root);
        while(!q.isEmpty()){

            int size = q.size();
            List<Integer> tmp = new ArrayList<>();

            for(int i = 0; i < size; i++){

                Node p = q.poll();
                tmp.add(p.val);

                for(int j = 0; j < p.children.size(); j++){
                    q.offer(p.children.get(j));
                }
            }

            res.add(tmp);
        }
        return res;
    }
}
