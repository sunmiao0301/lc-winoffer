对于“初始化”和“清除”问题
C++为我们引入了“构建器”的概念
Java沿用 但是进行了新增 称为“垃圾收集器” 能在资源不再需要的时候自动释放它们

4.1 用构造函数自动初始化
构造函数的名字与类名相同，如此可以保证这样一个方法可以在初始化期间自动调用
下面是带构造函数的一个类
class Rock {
  Rock() { // This is the constructor —— 构造函数
    System.out.println("Creating Rock");
  }
}
在这种情况下，一旦创建一个对象
new Rock();
就会分配相应的存储空间 并调用构造函数 以此保证对象正确的初始化 

构造函数也能使用自变量 利用构造函数的自变量，可以为一个对象的初始化设定相应的参数 如下代码
class Rock {
  Rock(int i) {
    System.out.println(
      "Creating Rock number " + i);
  }
}
...
int i = 1;
new Rock(i);

注意！构造函数属于一种比较特殊的方法类型 因为其本身没有返回值  这与void返回值存在着明显的区别

4.2 方法过载
在日常生活中，我们只会说：
1）洗 车、洗 衣服、洗 苹果
而不会去说：
2）洗车的洗 车、洗衣服的洗 衣服、洗苹果的洗 苹果
那么 1）其实就是一种洗方法的重载，尽管有着车、衣服、苹果三种不同类型的自变量
所以说 —— 为了让相同的方法名伴随不同的自变量类型使用、“方法过载”是非常关键的一项措施

4.2.1 区分过载方法
区分方法有
1）每个过载的方法都采用独一无二的自变量类型
2）甚至是自变量的顺序也足够我们区分两种过载的方法
public class OverloadingOrder {
  static void print(String s, int i) { 
    System.out.println(
      "String: " + s +
      ", int: " + i);
  }
  static void print(int i, String s) {
    System.out.println(
      "int: " + i +
      ", String: " + s);
  }
  public static void main(String[] args) {
    print("String first", 11);
    print(99, "Int first");
  }
}

4.2.3 返回值能否过载？
不能，比如
void f(int i) {}
int f(int i) {}
对于这两个方法，我们在实际代码中一般是调用方法而不关心其返回值 如
f()
在这种情况下 Java无法判断f()的具体调用方式

4.2.4 默认构建器 —— 简而言之就是要么不构造无参构造函数，系统就会帮你自动构造一个；如果你构造了含参的构造函数，但是没有构造无参构造函数，结果最后却调用了无参构造函数，那么结果就是报错（系统在你创建构造函数的时候就觉得你会考虑你需要的所有类型构造函数）

正如早先指出的那样，默认构建器是没有自变量的。它们的作用是创建一个“空对象”。若创建一个没有构建器的类，则编译程序会帮我们自动创建一个默认构建器。例如：
//: DefaultConstructor.java
class Bird {
  int i;
}
public class DefaultConstructor {
  public static void main(String[] args) {
    Bird nc = new Bird(); // default!
  }
} ///:~
对于下面这一行：
new Bird();
它的作用是新建一个对象，并调用默认构建器——即使尚未明确定义一个象这样的构建器。若没有它，就没有方法可以调用，无法构建我们的对象。然而，如果已经定义了一个构建器（无论是否有自变量），编译程序都不会帮我们自动合成一个：
class Bush {
Bush(int i) {}
Bush(double d) {}
}
现在，假若使用下述代码：
new Bush();
编译程序就会报告自己找不到一个相符的构建器。就好象我们没有设置任何构建器，编译程序会说：“你看来似乎需要一个构建器，所以让我们给你制造一个吧。”但假如我们写了一个构建器，编译程序就会说：“啊，你已写了一个构建器，所以我知道你想干什么；如果你不放置一个默认的，是由于你打算省略它。”

4.2.5 this关键字 —— 某个对象正在调用方法，如何在方法中得到那个对象？
我的理解是 
使用this的目的是：在一个方法的内部，却希望获得当前正在使用这个方法的对象的句柄，但是这个句柄并不会有标识符可用，比如：
class Banana { void f(int i) { /* ... */ } }
Banana a = new Banana(), b = new Banana();
a.f(1);
b.f(2);
可以发现，方法f()在被a、b调用的时候，是没有收到a、b的句柄作为参数的，那么如果想要得到他们，该怎么做？
就是this，
this关键字（注意只能在方法内部使用）可为已调用了其方法的那个对象生成相应的句柄。
例如，假若您希望将句柄返回给当前对象，那么它经常在return语句中使用。
public class Leaf {
  private int i = 0;
  Leaf increment() {
    i++;
    return this;
  }
  void print() {
    System.out.println("i = " + i);
  }
  public static void main(String[] args) {
    Leaf x = new Leaf();
    x.increment().increment().increment().print();
  }
} 
由于increment()通过this关键字返回当前对象的句柄，所以可以方便地对同一个对象执行多项操作！

/***
在构建器里调用构建器
若为一个类写了多个构建器，那么经常都需要在一个构建器里调用另一个构建器，以避免写重复的代码。可用this关键字做到这一点。

通常，当我们说this的时候，都是指“这个对象”或者“当前对象”。而且它本身会产生当前对象的一个句柄。在一个构建器中，若为其赋予一个自变量列表，那么this关键字会具有不同的含义：它会对与那个自变量列表相符的构建器进行明确的调用。这样一来，我们就可通过一条直接的途径来调用其他构建器。如下所示：

//: Flower.java
// Calling constructors with "this"

public class Flower {
  private int petalCount = 0;
  private String s = new String("null");
  Flower(int petals) {
    petalCount = petals;
    System.out.println(
      "Constructor w/ int arg only, petalCount= "
      + petalCount);
  }
  Flower(String ss) {
    System.out.println(
      "Constructor w/ String arg only, s=" + ss);
    s = ss;
  }
  Flower(String s, int petals) {
    this(petals);
//!    this(s); // Can't call two!
    this.s = s; // Another use of "this"
    System.out.println("String & int args");
  }
  Flower() {
    this("hi", 47);
    System.out.println(
      "default constructor (no args)");
  }
  void print() {
//!    this(11); // Not inside non-constructor!
    System.out.println(
      "petalCount = " + petalCount + " s = "+ s);
  }
  public static void main(String[] args) {
    Flower x = new Flower();
    x.print();
  }
} ///:~
其中，构建器Flower(String s,int petals)向我们揭示出这样一个问题：尽管可用this调用一个构建器，但不可调用两个。除此以外，构建器调用必须是我们做的第一件事情，否则会收到编译程序的报错信息。

这个例子也向大家展示了this的另一项用途。由于自变量s的名字以及成员数据s的名字是相同的，所以会出现混淆。为解决这个问题，可用this.s来引用成员数据。经常都会在Java代码里看到这种形式的应用，本书的大量地方也采用了这种做法。

在print()中，我们发现编译器不让我们从除了一个构建器之外的其他任何方法内部调用一个构建器。
***/

4.3 清除：收尾和垃圾收集
假定我们的对象分配了一个“特殊”内存区域，没有使用new。
垃圾收集器只知道释放那些由new分配的内存，所以不知道如何释放对象的“特殊”内存。
为解决这个问题，Java提供了一个名为finalize()的方法，可为我们的类定义它。
finalize()最有用处的地方之一是观察垃圾收集的过程。
下面这个例子向大家展示了垃圾收集所经历的过程，并对前面的陈述进行了总结。
//: Garbage.java
// Demonstration of the garbage
// collector and finalization
class Chair {
  static boolean gcrun = false;
  static boolean f = false;
  static int created = 0;
  static int finalized = 0;
  int i;
  Chair() {
    i = ++created;
    if(created == 47) 
      System.out.println("Created 47");
  }
  protected void finalize() {
    if(!gcrun) {
      gcrun = true;
      System.out.println(
        "Beginning to finalize after " +
        created + " Chairs have been created");
    }
    if(i == 47) {
      System.out.println(
        "Finalizing Chair #47, " +
        "Setting flag to stop Chair creation");
      f = true;
    }
    finalized++;
    if(finalized >= created)
      System.out.println(
        "All " + finalized + " finalized");
  }
}

public class Garbage {
  public static void main(String[] args) {
    if(args.length == 0) {
      System.err.println("Usage: \n" +
        "java Garbage before\n  or:\n" +
        "java Garbage after");
      return;
    }
    while(!Chair.f) {
      new Chair();
      new String("To take up space");
    }
    System.out.println(
      "After all Chairs have been created:\n" +
      "total created = " + Chair.created +
      ", total finalized = " + Chair.finalized);
    if(args[0].equals("before")) {
      System.out.println("gc():");
      System.gc();
      System.out.println("runFinalization():");
      System.runFinalization();
    }
    System.out.println("bye!");
    if(args[0].equals("after"))
      System.runFinalizersOnExit(true);
  }
} ///:~

4.4 成员初始化 —— unseen

4.5 数组初始化 —— unseen

4.6 总结

4.7 练习
(1) 用默认构建器创建一个类（没有自变量），用它打印一条消息。创建属于这个类的一个对象。
Class Example{
  Example(){
    System.out.println("hello, world");
  }
}
Class main(){
  public static void main(String[] args){
    Example e = new Example();
  }
}

(2) 在练习1的基础上增加一个过载的构建器，令其采用一个String自变量，并随同自己的消息打印出来。
Class Example{
  Example(){
    System.out.println("hello, world");
  }
  Example(String s){
    System.out.println(s);
  }
}
Class main(){
  public static void main(String[] args){
    Example e = new Example();
    Example e = new Example("hello, world");
  }
}

(3) 以练习2创建的类为基础上，创建属于它的对象句柄的一个数组，但不要实际创建对象并分配到数组里。运行程序时，注意是否打印出来自构建器调用的初始化消息。
Class Example{
  Example(){
    System.out.println("hello, world");
  }
  Example(String s){
    System.out.println(s);
  }
}
Class main(){
  public static void main(String[] args){
    Example[] arr = new Example[];
  }
}

(4) 创建同句柄数组联系起来的对象，最终完成练习3。
Class Example{
  Example(){
    System.out.println("hello, world");
  }
  Example(String s){
    System.out.println(s);
  }
}
Class main(){
  public static void main(String[] args){
    Example[] arr = new Example[2];
    arr[0] = new Example();
    arr[1] = new Example("hello, world");
  }
}

(5) 用自变量“before”，“after”和“none”运行程序，试验Garbage.java。重复这个操作，观察是否从输出中看出了一些固定的模式。改变代码，使System.runFinalization()在System.gc()之前调用，再观察结果。
