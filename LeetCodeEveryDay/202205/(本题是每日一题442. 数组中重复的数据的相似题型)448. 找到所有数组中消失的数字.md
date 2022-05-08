[448. 找到所有数组中消失的数字](https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/)

### 两种主流解题方法：

**1）通过交换数字到应在的位置上，思路如下:**
//如果是该在的位置 就到下一位 
//如果不是，并且该在的位置已经被占领并且是正确的数，continue
//如果不是，就放到该在的位置 然后继续在本位 
//最后遍历一遍即可

执行结果：

通过

显示详情

添加备注

执行用时：6 ms, 在所有 Java 提交中击败了39.58%的用户

内存消耗：49.1 MB, 在所有 Java 提交中击败了71.95%的用户

通过测试用例：33 / 33

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //遍历 -- 
        // 4322
        // 2324
        // 3224
        // 2234
        //如果是该在的位置 就到下一位 
        //如果不是，并且该在的位置已经被占领并且是正确的数，continue
        //如果不是，就放到该在的位置 然后继续在本位 
        //最后遍历一遍即可
        List<Integer> res = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == i + 1){
                continue;
            }
            // 3 该在的位置是nums[2]
            // 本 if 表示已经在该在的位置了
            if(nums[nums[i] - 1] == nums[i]){
                continue;
            }
            else{
                int tmp = nums[i];
                nums[i] = nums[nums[i] - 1];
                nums[tmp - 1] = tmp;
                i--;
            }
        }
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i + 1){
                res.add(i + 1);
            }
        }
        return res;
    }
}
```

**2）通过对数组中的数组进行修改，进而存储信息（这种方法更普适，即使是比这两种题型更复杂的题型也能解决（比如找到重复三次的数之类的））**
//具体的修改方案也有很多种，如果是找重复两次的，就可以用正负号解决，如果是多次，就可以用加n解决，最后遍历一遍的时候，除以n就行了。

这种方法效率高了很多：

执行结果：

通过

显示详情

添加备注

执行用时：3 ms, 在所有 Java 提交中击败了99.73%的用户

内存消耗：49 MB, 在所有 Java 提交中击败了86.04%的用户

通过测试用例：33 / 33

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int iAfterMod = (nums[i] - 1) % n;
            nums[iAfterMod] += n;
        }
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(nums[i] <= n){
                res.add(i + 1);
            }
        }
        return res;
    }
}
```

