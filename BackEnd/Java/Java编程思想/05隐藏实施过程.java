5 隐藏实施过程
public protected private
如何将组件绑定到单独的一个统一的库单元里，这是通过Java的package（打包）关键字来实现的，而且访问指示符要受到类在相同的包还是在不同的包里的影响

5.1 包：库单元
为Java创建一个源码文件的时候，通常叫做一个“编译单元” 每个编译单元都必须有一个以.java结尾的名字
并且在编译单元的内部，有的话，就只能有一个public类，并且其必须拥有与这个编译单元相同的名字
---该编译单元剩下的类，可在那个包外面的世界隐藏起来，因为他们并非是public的

编译一个.java文件时，我们会获得一个名字完全相同的输出文件；
但对于.java文件中的每个类，它们都有一个.class扩展名。
因此，我们最终从少量的.java文件里有可能获得数量众多的.class文件。

类 -> 之上是 库

作为一名库设计者，
一定要记住package和import关键字允许我们做的事情就是分割单个全局命名空间，
保证我们不会遇到名字的冲突——无论有多少人使用因特网，
也无论多少人用Java编写自己的类。

5.1.1 创建独一无二的包名
Java解释器的工作程序如下：
首先，它找到环境变量CLASSPATH（将Java或者具有Java解释能力的工具——如浏览器——安装到机器中时，通过操作系统进行设定）。
CLASSPATH包含了一个或多个目录，它们作为一种特殊的“根”使用，从这里展开对.class文件的搜索。
从那个根开始，解释器会寻找包名，并将每个点号（句点）替换成一个斜杠
从而生成从CLASSPATH根开始的一个路径名（所以package foo.bar.baz会变成foo\bar\baz或者foo/bar/baz；具体是正斜杠还是反斜杠由操作系统决定）。
随后将它们连接到一起，成为CLASSPATH内的各个条目（入口）。以后搜索.class文件时，就可从这些地方开始查找与准备创建的类名对应的名字。
此外，它也会搜索一些标准目录——这些目录与Java解释器驻留的地方有关。
为进一步理解这个问题，下面以我自己的域名为例，它是bruceeckel.com。
将其反转过来后，com.bruceeckel就为我的类创建了独一无二的全局名称（com，edu，org，net等扩展名以前在Java包中都是大写的，
但自Java 1.2以来，这种情况已发生了变化。现在整个包名都是小写的）。
由于决定创建一个名为util的库，我可以进一步地分割它，所以最后得到的包名如下：
package com.bruceeckel.util;
现在，可将这个包名作为下述两个文件的“命名空间”使用：
//: Vector.java
// Creating a package
package com.bruceeckel.util;

public class Vector {
  public Vector() {
    System.out.println(
      "com.bruceeckel.util.Vector");
  }
} ///:~
创建自己的包时，要求package语句必须是文件中的第一个“非注释”代码。第二个文件表面看起来是类似的：
//: List.java
// Creating a package 
package com.bruceeckel.util;

public class List {
  public List() {
    System.out.println(
      "com.bruceeckel.util.List");
  }
} ///:~
这两个文件都置于我自己系统的一个子目录中：
C:\DOC\JavaT\com\bruceeckel\util
若通过它往回走，就会发现包名com.bruceeckel.util，但路径的第一部分又是什么呢？这是由CLASSPATH环境变量决定的。在我的机器上，它是：

CLASSPATH=.;D:\JAVA\LIB;C:\DOC\JavaT
可以看出，CLASSPATH里能包含大量备用的搜索路径。然而，使用JAR文件时要注意一个问题：必须将JAR文件的名字置于类路径里，而不仅仅是它所在的路径。所以对一个名为grape.jar的JAR文件来说，我们的类路径需要包括：

CLASSPATH=.;D:\JAVA\LIB;C:\flavors\grape.jar
正确设置好类路径后，可将下面这个文件置于任何目录里（若在执行该程序时遇到麻烦，请参见第3章的3.1.2小节“赋值”）：

//: LibTest.java
// Uses the library
package c05;
import com.bruceeckel.util.*;

public class LibTest {
  public static void main(String[] args) {
    Vector v = new Vector();
    List l = new List();
  }
} ///:~
编译器遇到import语句后，
它会搜索由CLASSPATH指定的目录，
查找子目录com\bruceeckel\util，
然后查找名称适当的已编译文件（对于Vector是Vector.class，对于List则是List.class）。
注意Vector和List内无论类还是需要的方法都必须设为public。

5.1.2 自定义工具库

掌握前述的知识后，接下来就可以开始创建自己的工具库，以便减少或者完全消除重复的代码。
例如，可为System.out.println()创建一个别名，减少重复键入的代码量。它可以是名为tools的一个包（package）的一部分：

//: P.java
// The P.rint & P.rintln shorthand
package com.bruceeckel.tools;

public class P {
  public static void rint(Object obj) {
    System.out.print(obj);
  }
  public static void rint(String s) {
    System.out.print(s);
  }
  public static void rint(char[] s) {
    System.out.print(s);
  }
  public static void rint(char c) {
    System.out.print(c);
  }
  public static void rint(int i) {
    System.out.print(i);
  }
  public static void rint(long l) {
    System.out.print(l);
  }
  public static void rint(float f) {
    System.out.print(f);
  }
  public static void rint(double d) {
    System.out.print(d);
  }
  public static void rint(boolean b) {
    System.out.print(b);
  }
  public static void rintln() {
    System.out.println();
  }
  public static void rintln(Object obj) {
    System.out.println(obj);
  }
  public static void rintln(String s) {
    System.out.println(s);
  }
  public static void rintln(char[] s) {
    System.out.println(s);
  }
  public static void rintln(char c) {
    System.out.println(c);
  }
  public static void rintln(int i) {
    System.out.println(i);
  }
  public static void rintln(long l) {
    System.out.println(l);
  }
  public static void rintln(float f) {
    System.out.println(f);
  }
  public static void rintln(double d) {
    System.out.println(d);
  }
  public static void rintln(boolean b) {
    System.out.println(b);
  }
} ///:~
所有不同的数据类型现在都可以在一个新行输出（P.rintln()），或者不在一个新行输出（P.rint()）。 
大家可能会猜想这个文件所在的目录必须从某个CLASSPATH位置开始，然后继续com/bruceeckel/tools。
编译完毕后，利用一个import语句，即可在自己系统的任何地方使用P.class文件。如下所示：
ToolTest.java
所以从现在开始，无论什么时候只要做出了一个有用的新工具，就可将其加入tools目录（或者自己的个人util或tools目录）。

5.2 Java访问指示符

5.2.1 “友好的”
不指定访问指示符，就是“友好的”
这意味着当前 包内所有的其他类都能访问“友好的”成员，但对于包外的所有类来说，这些成员却是“私有”的，外界不能访问。

5.2.2 public
所有人从任何地方都能访问

5.2.3 private 
private关键字意味着除非那个特定的类而且从那个类的特定方法里，否则没有人能访问那个成员。
同一个包内的其他成员不能访问private成员，这使其显得似乎将类与我们自己都隔离起来。
---随着学习的深入，你将发现private仍具有非常重要的用途，特别是涉及多线程处理的时候。

下面是应用了private的一个例子：
//: IceCream.java
// Demonstrates "private" keyword
class Sundae {
  private Sundae() {}
  static Sundae makeASundae() { 
    return new Sundae(); 
  }
}

public class IceCream {
  public static void main(String[] args) {
    //! Sundae x = new Sundae(); 控制对象的创建方式 无法直接用无参构造函数创建 因为是private
    Sundae x = Sundae.makeASundae(); // 只能通过makeaSundae来实现
  }
} ///:~
这个例子向我们证明了使用private的方便：
有时可能想控制对象的创建方式，并防止有人直接访问一个特定的构建器
在上面的例子中，我们不可通过它的构建器创建一个Sundae对象；
相反，必须调用makeASundae()方法来实现

5.2.4 protected 友好的一种 但是相较于友好不能让其子类访问它 所以就需要用到protected
也就是说：protected比默认友好的 还多了一种继承它而得到的子类也能访问的特性

5.3 接口与实现
定义和实现分开

5.4 类访问 —— unseen
PS：对于相同目录内的所有文件，如果没有明确的进行package声明，那么它们都默认为那个目录的默认包的一部分

5.5 总结
访问控制的原因
1）防止用户触碰那些他们不应该触碰的东西
2）允许库设计者改变类的内部工作机制 同时不必担心他会对客户程序员有什么影响

5.6 练习
(1) 用public、private、protected以及“友好的”数据成员及方法成员创建一个类。创建属于这个类的一个对象，并观察在试图访问所有类成员时会获得哪种类型的编译器错误提示。注意同一个目录内的类属于“默认”包的一部分。

(2) 用protected数据创建一个类。在相同的文件里创建第二个类，用一个方法操纵第一个类里的protected数据。
1)继承
2)默认在同一个包中
3)static

(3) 新建一个目录，并编辑自己的CLASSPATH，以便包括那个新目录。将P.class文件复制到自己的新目录，然后改变文件名、P类以及方法名（亦可考虑添加额外的输出，观察它的运行过程）。在一个不同的目录里创建另一个程序，令其使用自己的新类。

(4) 在c05目录（假定在自己的CLASSPATH里）创建下述文件：

214页程序

然后在c05之外的另一个目录里创建下述文件：

214-215页程序

解释编译器为什么会产生一个错误。将Foreign（外部）类作为c05包的一部分改变了什么东西吗？








