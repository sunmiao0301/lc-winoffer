## 看了题解写的 -- 不要用海伦公式，用行列式"https://www.bilibili.com/read/cv4236107

#### 方法一：枚举

![image-20220515134218265](https://raw.githubusercontent.com/sunmiao0301/Public-Pic-Bed/main/imgfromPicGO/202205151342429.png)

执行结果：
通过
显示详情
添加备注

执行用时：
6 ms
, 在所有 Java 提交中击败了
33.02%
的用户
内存消耗：
39.3 MB
, 在所有 Java 提交中击败了
19.48%
的用户
通过测试用例：
57 / 57

```java
class Solution {
    public double largestTriangleArea(int[][] points) {
        //三角形面积公式 -- 海拉
        int len = points.length;
        double res = 0.0;
        for(int i = 0; i < len; i++){
            for(int j = i + 1; j < len; j++){
                for(int k = j + 1; k < len; k++){
                    double x1 = points[i][0];
                    double y1 = points[i][1];
                    double x2 = points[j][0];
                    double y2 = points[j][1];
                    double x3 = points[k][0];
                    double y3 = points[k][1];
                    res = Math.max(res, calculator(x1, y1, x2, y2, x3, y3));
                }
            }
        }
        return res;
    }
    public double calculator(double x1, double y1, double x2, double y2, double x3, double y3){
        return Math.abs(x1 * y2 + x2 * y3 + x3 * y1 - x1 * y3 - x2 * y1 - x3 * y2) / 2;
    }
}
```



#### 方法二：凸包812. 最大三角形面积
