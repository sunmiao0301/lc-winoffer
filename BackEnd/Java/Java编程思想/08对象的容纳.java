8.对象的容纳
Java提供了容纳对象（或对象的句柄）的多种方式
1）内建类型 —— 数组
2）集合类（容器类）

8.1 数组
与C++不同的是 Java对数组会进行范围检查，若是超过边界，就会获得一个 RuntimeException(运行时间违例）

8.1.2 （Java中）数组的返回 —— easy

8.2 集合
在提集合具体内容之前 我们可以思考一个问题：
集合也是一个类，在程序员最初编写这些集合的代码时，他不知道用户到底想将什么类型放入集合，怎么解决？
答：当初编写的时候，程序员设计的集合实际上容纳的是类型为Object的一些对象的句柄，Object是所有类的根，自然代表Java中的所有对象
PS：注意！但是这并不包括基本数据类型，因为它们并不是从“任何东西”继承来的

1.错误有时并不显露出来 —— unseen

1.参数化类型

8.3 枚举器（反复器）
想要在不清楚集合具体数据结构的情况下，对一个集合中的对象进行遍历，就可以利用“反复器 Iterator” 
具体下代码：
//: CatsAndDogs2.java
// Simple collection with Enumeration
import java.util.*;

class Cat2 { //Cat类
  private int catNumber;
  Cat2(int i) {
    catNumber = i;
  }
  void print() {
    System.out.println("Cat number " +catNumber);
  }
}

class Dog2 { // Dog类
  private int dogNumber;
  Dog2(int i) {
    dogNumber = i;
  }
  void print() {
    System.out.println("Dog number " +dogNumber);
  }
}

public class CatsAndDogs2 {
  public static void main(String[] args) {
    Vector cats = new Vector();
    for(int i = 0; i < 7; i++)
      cats.addElement(new Cat2(i));
    // Not a problem to add a dog to cats:
    cats.addElement(new Dog2(7));
    Enumeration e = cats.elements();
    /*
    使用一个Enumeration遍历整个序列
    可以看到不需关心集合中的元素数量
    也就是说 我们拥有的全部东西就便是Enumeration
    它可以取得下一个对象 也可知道是否抵达了末尾
    */
    while(e.hasMoreElements())
      ((Cat2)e.nextElement()).print();
    // Dog is detected only at run-time
  }
} ///:~

