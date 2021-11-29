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

8.4 集合的类型

1.崩溃Java
假定我们此时想打印出自己类的地址
看似可以想下面一样操作：
//: CrashJava.java
// One way to crash Java
import java.util.*;

public class CrashJava {
  public String toString() {
    return "CrashJava address: " + this + "\n"; // 使用this
  }
  public static void main(String[] args) {
    Vector v = new Vector();
    for(int i = 0; i < 10; i++)
      v.addElement(new CrashJava());
    System.out.println(v);
  }
} ///:~
但是实际上这会导致崩溃
原因是遇到 "CrashJava address: " + this + "\n  
的时候 编译器想要调用 toString() 来将 this 转化为字串 但这会导致递归调用 最终导致堆栈溢出
所以不会出现违例控制（没有机会做出响应）
所以想要得到类地址的正确的方法 是
调用Object的 toString() 方法 并且不需要 this 使用super.toString()即可
前提是方法是从Object直接继承来的 或者根本没有一个父类覆盖了 toString() 方法

8.4.3 Stack
Vector操作亦可针对Stack对象进行 因为Stack“属于”一种Vector （继承）
所以能对Vector进行的操作亦可对Stack进行 例如 elementAt()

8.4.4 Hashtable
散列表的具体工作原理已超出本书范围 —— 只需要知道散列表是一个快速“字典” —— hashCode() 使得性能大幅提升

8.4.5 再论枚举器 —— unseen                                                       

8.5 排序 —— 主要在提“如何比较两个对象，并且将要发生变化的东西封装在内
//: Compare.java
// Interface for sorting callback:
package c08;

interface Compare {
  boolean lessThan(Object lhs, Object rhs);
  boolean lessThanOrEqual(Object lhs, Object rhs);
} ///:~

8.6 通用集合库

8.7 新集合
Java 1.2 中加入了新的行为：链表、队列、双端队列
新的集合分割为两个概念：
1）集合 Collection —— 一组单独的元素，通常应用了某种规则 
2）映射 Map —— 一系列“键值对” 
----实际上只有三个集合组件 Map List Set

//: SimpleCollection.java
// A simple example using the new Collections
package c08.newcollections;
import java.util.*;

public class SimpleCollection {
  public static void main(String[] args) {
    /*
    main()的第一行创建了一个ArrayList对象，然后将其上溯造型成为一个集合。
    由于这个例子只使用了Collection方法，所以从Collection继承的一个类的任何对象都可以正常工作。
    */
    Collection c = new ArrayList();
    for(int i = 0; i < 10; i++)
      c.add(Integer.toString(i));
    Iterator it = c.iterator();
    while(it.hasNext())
      System.out.println(it.next());
  }
} ///:~
利用iterator()方法，所有集合都能生成一个“反复器”（Iterator）

----LinkedList与ArrayList对比
1）ArrayList
可以像数组一样快速访问元素
但当从列表中部插入和删除元素时，速度却稍慢
一般用于向前或向后遍历元素
2）LinkedList
高效率地在列表中部进行插入和删除操作
但是在随机访问时，速度却很慢
同时也提供了 addFirst()、addLast()、getFirst()、getLast()、removeFirst()、removeLast()等方法
以便将其作为一个队列、双向队列等使用。

8.7.3 使用Sets
一个Set只允许每个对象存在一个实例
也就是说 重复的值被添加到Set中 在打印的时候 Set只接受每个值的一个实例

8.7.4 使用Maps
Maps维持 键值 对应关系对 以便通过一个键查找相应的值

1.决定使用何种Set
在ArraySet和HashSet间作出选择 具体取决于Set的大小
如果需要从一个Set中获得一个顺序列表 用TreeSet

8.7.7 排序和搜索
用于排序和搜索数组的 Arrays 、 Collections

1.Collections或Map的同步
synchronized关键字是“多线程”机制一个非常重要的部分。
但是在这儿只需注意到Collections类提供了对整个容器进行自动同步的一种途径。
----新集合提供了防止多进程同时修改一个容器内容 
—— 能查出出我们的进程之外的对容器的修改，若探测到，会立即产生一个 ConcurrentModificationException(并发修改违例)
这一机制被称为“立即失败”

8.8 总结
下面复习一下由标准Java（1.0和1.1）库提供的集合（BitSet未包括在这里，因为它更象一种负有特殊使命的类）：

(1) 数组包含了对象的数字化索引。它容纳的是一种已知类型的对象，所以在查找一个对象时，不必对结果进行造型处理。数组可以是多维的，而且能够容纳基本数据类型。但是，一旦把它创建好以后，大小便不能变化了。

(2) Vector（矢量）也包含了对象的数字索引——可将数组和Vector想象成随机访问集合。当我们加入更多的元素时，Vector能够自动改变自身的大小。但Vector只能容纳对象的句柄，所以它不可包含基本数据类型；而且将一个对象句柄从集合中取出来的时候，必须对结果进行造型处理。

(3) Hashtable（散列表）属于Dictionary（字典）的一种类型，是一种将对象（而不是数字）同其他对象关联到一起的方式。散列表也支持对对象的随机访问，事实上，它的整个设计方案都在突出访问的“高速度”。

(4) Stack（堆栈）是一种“后入先出”（LIFO）的队列。

若你曾经熟悉数据结构，可能会疑惑为何没看到一套更大的集合。从功能的角度出发，你真的需要一套更大的集合吗？对于Hashtable，可将任何东西置入其中，并以非常快的速度检索；对于Enumeration（枚举），可遍历一个序列，并对其中的每个元素都采取一个特定的操作。那是一种功能足够强劲的工具。

8.9 练习
(1) 新建一个名为Gerbil的类，在构建器中初始化一个int gerbilNumber（类似本章的Mouse例子）。为其写一个名为hop()的方法，用它打印出符合hop()条件的Gerbil的编号。建一个Vector，并为Vector添加一系列Gerbil对象。现在，用elementAt()方法在Vector中遍历，并为每个Gerbil都调用hop()。

(2) 修改练习1，用Enumeration在调用hop()的同时遍历Vector。

(3) 在AssocArray.java中，修改这个例子，令其使用一个Hashtable，而不是AssocArray。

(4) 获取练习1用到的Gerbil类，改为把它置入一个Hashtable，然后将Gerbil的名称作为一个String（键）与置入表格的每个Gerbil（值）都关联起来。获得用于keys()的一个Enumeration，并用它在Hashtable里遍历，查找每个键的Gerbil，打印出键，然后将gerbil告诉给hop()。

(5) 修改第7章的练习1，用一个Vector容纳Rodent（啮齿动物），并用Enumeration在Rodent序列中遍历。记住Vector只能容纳对象，所以在访问单独的Rodent时必须采用一个造型（如RTTI）。

(6) 转到第7章的中间位置，找到那个GreenhouseControls.java（温室控制）例子，该例应该由三个文件构成。在Controller.java中，类EventSet仅是一个集合。修改它的代码，用一个Stack代替EventSet。当然，这时可能并不仅仅用Stack取代EventSet这样简单；也需要用一个Enumeration遍历事件集。可考虑在某些时候将集合当作Stack对待，另一些时候则当作Vector对待——这样或许能使事情变得更加简单。

(7) （有一定挑战性）在与所有Java发行包配套提供的Java源码库中找出用于Vector的源码。复制这些代码，制作名为 intVector的一个特殊版本，只在其中包含int数据。思考是否能为所有基本数据类型都制作Vector的一个特殊版本。接下来，考虑假如制作一个链接列表类，令其能随同所有基本数据类型使用，那么会发生什么情况。若在Java中提供了参数化类型，利用它们便可自动完成这一工作（还有其他许多好处）。

