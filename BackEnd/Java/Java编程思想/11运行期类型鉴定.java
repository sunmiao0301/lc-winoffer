运行期类型鉴定 —— RTTI —— 手上只有基础类型的一个句柄时，利用它判断一个对象的正确类型
方法有2
1）传统的RTTI —— 假定我们在编译和运行期拥有所有类型
2）“反射机制” —— 在运行期独立查找类信息

11.1 对RTTI的需要
打个比方，现在有一个“形状”接口
实现得到三个形状类 ⚪ ❤ 🔺
我们可以创建一个特定的对象 Circle、Square 或是 Triangle
然后上溯造型为一个Shape而忽略对象的特殊类型 以便在程序的剩余部分使用匿名的Shape句柄
但是此时如果我们想找出某一种特定图形（如🔺）此时就需要RTTI技术、用于查询某个 Shape 句柄引用的准确类型是什么

11.1.1 Class对象 ！！！
为理解RTTI在Java里如何工作，首先必须了解类型信息在运行期是如何表示的。
这时要用到一个名为“Class对象”的特殊形式的对象，其中包含了与类有关的信息（有时也把它叫作“元类”）。
事实上，我们要用Class对象创建属于某个类的全部“常规”或“普通”对象。

对于作为程序一部分的每个类，它们都有一个Class对象。
换言之，每次写一个新类时，同时也会创建一个Class对象（更恰当地说，是保存在一个完全同名的.class文件中）。
在运行期，一旦我们想生成那个类的一个对象，用于执行程序的Java虚拟机（JVM）首先就会检查那个类型的Class对象是否已经载入。
若尚未载入，JVM就会查找同名的.class文件，并将其载入。
所以Java程序启动时并不是完全载入的，这一点与许多传统语言都不同。

一旦那个类型的Class对象进入内存，就用它创建那一类型的所有对象。

若这种说法多少让你产生了一点儿迷惑，或者并没有真正理解它，下面这个示范程序或许能提供进一步的帮助：

//: SweetShop.java
// Examination of the way the class loader works

class Candy {
  static {
    System.out.println("Loading Candy");
  }
}

class Gum {
  static {
    System.out.println("Loading Gum");
  }
}

class Cookie {
  static {
    System.out.println("Loading Cookie");
  }
}

public class SweetShop {
  public static void main(String[] args) {
    System.out.println("inside main");
    new Candy();
    System.out.println("After creating Candy");
    try {
      Class.forName("Gum");
    } catch(ClassNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println(
      "After Class.forName(\"Gum\")");
    new Cookie();
    System.out.println("After creating Cookie");
  }
} ///:~
对每个类来说（Candy，Gum和Cookie），它们都有一个static从句，用于在类首次载入时执行。相应的信息会打印出来，告诉我们载入是什么时候进行的。在main()中，对象的创建代码位于打印语句之间，以便侦测载入时间。 特别有趣的一行是：

Class.forName("Gum");
该方法是Class（即全部Class所从属的）的一个static成员。
而Class对象和其他任何对象都是类似的，所以能够获取和控制它的一个句柄（装载模块就是干这件事的）。
为获得Class的一个句柄，一个办法是使用forName()。
它的作用是取得包含了目标类文本名字的一个String（注意拼写和大小写）。最后返回的是一个Class句柄。

该程序在某个JVM中的输出如下：
inside main
Loading Candy
After creating Candy
Loading Gum
After Class.forName("Gum")
Loading Cookie
After creating Cookie

可以看到，每个Class只有在它需要的时候才会载入，而static初始化工作是在类载入时执行的。 非常有趣的是，另一个JVM的输出变成了另一个样子：
Loading Candy
Loading Cookie
inside main
After creating Candy
Loading Gum
After Class.forName("Gum")
After creating Cookie
看来JVM通过检查main()中的代码，已经预测到了对Candy和Cookie的需要，
但却看不到Gum，因为它是通过对forName()的一个调用创建的，而不是通过更典型的new调用。
尽管这个JVM也达到了我们希望的效果，因为确实会在我们需要之前载入那些类，但却不能肯定这儿展示的行为百分之百正确。

11.1.2 造型前的检查 —— unseen

11.2 RTTI语法 —— unclare

11.3 —— 反射：运行期类信息
RTTI虽然能得到对象的准确类型，但是前提是类型必须是在编译期间就已知的
“反射”提供了一种特殊机制，可以侦测可用方法，并产生方法名
----在Java 1.1中，Class类（本章前面已有详细论述）得到了扩展，可以支持“反射”的概念。
针对Field，Method以及Constructor类（每个都实现了Memberinterface——成员接口），它们都新增了一个库：java.lang.reflect。
这些类型的对象都是JVM在运行期创建的，用于代表未知类里对应的成员。
这样便可用构建器创建新对象，用get()和set()方法读取和修改与Field对象关联的字段，以及用invoke()方法调用与Method对象关联的方法。
此外，我们可调用方法getFields()，getMethods()，getConstructors()，
分别返回用于表示字段、方法以及构建器的对象数组（在联机文档中，还可找到与Class类有关的更多的资料）。
因此，匿名对象的类信息可在运行期被完整的揭露出来，而在编译期间不需要知道任何东西。 

大家要认识的很重要的一点是“反射”并没有什么神奇的地方。
通过“反射”同一个未知类型的对象打交道时，JVM只是简单地检查那个对象，并调查它从属于哪个特定的类（就象以前的RTTI那样）。
但在这之后，在我们做其他任何事情之前，Class对象必须载入。
因此，用于那种特定类型的.class文件必须能由JVM调用（要么在本地机器内，要么可以通过网络取得）。
所以RTTI和“反射”之间唯一的区别就是

对RTTI来说，编译器会在编译期打开和检查.class文件。
换句话说，我们可以用“普通”方式调用一个对象的所有方法;
但对“反射”来说，.class文件在编译期间是不可使用的，而是由运行期环境打开和检查。

11.4 总结 —— unclare
RTTI有时能解决效率问题。若代码大量运用了多形性，但其中的一个对象在执行效率上很有问题，便可用RTTI找出那个类型，然后写一段适当的代码，改进其效率。

11.5 练习
(1) 写一个方法，向它传递一个对象，循环打印出对象层次结构中的所有类。

(2) 在ToyTest.java中，将Toy的默认构建器标记成注释信息，解释随之发生的事情。

(3) 新建一种类型的集合，令其使用一个Vector。捕获置入其中的第一个对象的类型，然后从那时起只允许用户插入那种类型的对 象。

(4) 写一个程序，判断一个Char数组属于基本数据类型，还是一个真正的对象。

(5) 根据本章的说明，实现clearSpitValve()。

(6) 实现本章介绍的rotate(Shape)方法，令其检查是否已经旋转了一个圆（若已旋转，就不再执行旋转操作）。




