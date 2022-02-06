# WinOffer

**[https://leetcode-cn.com/](https://leetcode-cn.com/)**

| 类型                                                         | 常见题型                          | 备注                                                         |
| ------------------------------------------------------------ | --------------------------------- | ------------------------------------------------------------ |
| [01、数组](https://github.com/sunmiao0301/LC-Winoffer/tree/main/LeetCodeForkedFromLCmaster/01、数组) | 有序数组                          | 二分法                                                       |
|                                                              | 双指针                            |                                                              |
|                                                              | 滑动窗口                          | 一般用双指针来模拟滑动窗口                                   |
|                                                              | 二维数组（螺旋矩阵）              | 注意二维数组中有一维或者二维都是null的情况<br>注意数组是引用类型，且null和length == 0是不一样的 |
| [02、链表](https://github.com/sunmiao0301/LC-Winoffer/tree/main/LeetCodeForkedFromLCmaster/02、链表) | 哨兵结点sentinel                  |                                                              |
|                                                              | 链表数据结构及实现                |                                                              |
|                                                              | 经典 -- 反转链表                  | 掌握海象之谜和哨兵节点，你对链表问题的迭代解法会变得像诗一样优雅<br>但是递归法仍有问题 |
|                                                              | 两两对换                          |                                                              |
|                                                              | 经典 -- 删除链表的倒数第 N 个结点 | 快慢指针                                                     |
|                                                              | 找**链表相交**的节点              | 无论如何，最终总会 a == b                                    |
|                                                              | 环形链表Ⅰ，Ⅱ                      |                                                              |
| [03、哈希表](https://github.com/sunmiao0301/LC-Winoffer/tree/main/LeetCodeForkedFromLCmaster/03、哈希表) |                                   |                                                              |
| [04、字符串](https://github.com/sunmiao0301/LC-Winoffer/tree/main/LeetCodeForkedFromLCmaster/04、字符串) | 反转字符串Ⅰ，Ⅱ                    | 1）swap函数可以用位运算实现<br>2）对字符串进行原地替换，可以用<br>`char[] ch = s.toStringArray();`<br>`return new String(ch);` |
|                                                              | 替换空格(或是替换其他)            | 1）对字符串进行（非原地替换）新建和替换，可以用StringBuider类的方法append()，其中append既可以跟字符串s，也可以字符c作为参数 |
|                                                              | 翻转字符串里的单词                | 清空StringBuilder sb<br>`sb.delete(0, sb.length());`         |
|                                                              | 左旋转字符串                      | StringBuilder                                                |
|                                                              |                                   | KMP算法                                                      |
|                                                              |                                   |                                                              |
| [05、双指针法](https://github.com/sunmiao0301/LC-Winoffer/tree/main/LeetCodeForkedFromLCmaster/05、双指针法) |                                   |                                                              |
| [06、栈与队列](https://github.com/sunmiao0301/LC-Winoffer/tree/main/LeetCodeForkedFromLCmaster/06、栈与队列) |                                   |                                                              |
| [07、二叉树](https://github.com/sunmiao0301/LC-Winoffer/tree/main/LeetCodeForkedFromLCmaster/07、二叉树) | 深度优先遍历（前中后序遍历）DFS   | 1）递归法<br>2）迭代法（由于递归就是隐形栈，所以递归能做的，栈也能）<br>迭代法中序遍历与前后序遍历略有不同 |
|                                                              | 广度优先遍历BFS（层序遍历）       | 只用一个队列存储每层节点的话，需要用一个整数值存储上一层的节点数<br>1）一个用于返回的List<List< Integer>> <br>2）一个用于存储某层节点的list< TreeNode><br>3）一个用于存储某层节点值的list< Integer><br>4）整数值i用于记载list< TreeNode>中当前有多少节点是上一层的 |
|                                                              | 翻转二叉树                        | homebrew<br>下面的四行代码很好用<br>`        TreeNode left = invertTree(root.left);`<br>`TreeNode right = invertTree(root.right);`<br>`root.left = right;`<br>`root.right = left;` |
|                                                              | 对称二叉树                        |                                                              |
|                                                              | 二叉树的最大深度                  | 无需helper()                                                 |
|                                                              | 二叉树的最小深度                  | 和最大深度代码结构一致                                       |
|                                                              | 完全二叉树的节点个数              | 1）二分查找 + 位运算<br>2）DFS暴力                           |
|                                                              | 平衡二叉树                        | 自底向上 快<br>自顶向下 慢                                   |
|                                                              | 二叉树的所有路径                  | 参数传递的本质依旧是GRoE                                     |
| [08、回溯算法](https://github.com/sunmiao0301/LC-Winoffer/tree/main/LeetCodeForkedFromLCmaster/08、回溯算法) |                                   |                                                              |
| [09、贪心算法](https://github.com/sunmiao0301/LC-Winoffer/tree/main/LeetCodeForkedFromLCmaster/09、贪心算法) |                                   |                                                              |
| [10、动态规划](https://github.com/sunmiao0301/LC-Winoffer/tree/main/LeetCodeForkedFromLCmaster/10、动态规划) |                                   |                                                              |
| [11、单调栈](https://github.com/sunmiao0301/LC-Winoffer/tree/main/LeetCodeForkedFromLCmaster/11、单调栈) |                                   |                                                              |

### Log

##### 2021.4.18 第一次力扣

##### 2022.1.4 康复训练

##### 2022.1.13 康复训练结束 继续[按标签做题](https://github.com/sunmiao0301/leetcode-master)

##### 2022.1.17 解决一下pointer问题[我的总结](https://github.com/sunmiao0301/LC-Winoffer/blob/main/LeetCodeForkedFromLCmaster/07%E3%80%81%E4%BA%8C%E5%8F%89%E6%A0%91/31%E3%80%81%E4%BA%8C%E5%8F%89%E6%A0%91%EF%BC%9A%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E5%88%A0%E9%99%A4%E6%93%8D%E4%BD%9C%20-%20450.%20%E5%88%A0%E9%99%A4%E4%BA%8C%E5%8F%89%E6%90%9C%E7%B4%A2%E6%A0%91%E4%B8%AD%E7%9A%84%E8%8A%82%E7%82%B9.md)

##### 2022.1.22 第二遍 有序数组 -> 二分 -> 左闭右闭区间or**左闭右开区间**
