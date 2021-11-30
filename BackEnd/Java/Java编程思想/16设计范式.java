16.1 范式的概念

16.1.1 单子

或许最简单的设计范式就是“单子”（Singleton），它能提供对象的一个（而且只有一个）实例。单子在Java库中得到了应用，但下面这个例子显得更直接一些：

//: SingletonPattern.java
// The Singleton design pattern: you can
// never instantiate more than one.
package c16;

// Since this isn't inherited from a Cloneable
// base class and cloneability isn't added,
// making it final prevents cloneability from
// being added in any derived classes:
final class Singleton {
  private static Singleton s = new Singleton(47);
  private int i;
  private Singleton(int x) { i = x; }
  public static Singleton getHandle() { 
    return s; 
  }
  public int getValue() { return i; }
  public void setValue(int x) { i = x; }
}

public class SingletonPattern {
  public static void main(String[] args) {
    Singleton s = Singleton.getHandle();
    System.out.println(s.getValue());
    Singleton s2 = Singleton.getHandle();
    s2.setValue(9);
    System.out.println(s.getValue());
    try {
      // Can't do this: compile-time error.
      // Singleton s3 = (Singleton)s2.clone();
    } catch(Exception e) {}
  }
} ///:~
创建单子的关键就是防止客户程序员采用除由我们提供的之外的任何一种方式来创建一个对象。
必须将所有构建器都设为private（私有），而且至少要创建一个构建器，以防止编译器帮我们自动同步一个默认构建器
（它会自做聪明地创建成为“友好的”——friendly，而非private）。

16.2 观察器范式
由于某些对象的状态发生了改变，所以一组对象都需要更新，那么该如何解决？

16.3 模拟垃圾回收站
垃圾抵达垃圾回收站时，它们全都是混合在一起的。
程序必须为那些垃圾的分类定出一个模型。
这正是RTTI发挥作用的地方：我们有大量不知名的垃圾，程序将正确判断出它们所属的类型。
//: RecycleA.java 
// Recycling with RTTI
package c16.recyclea;
import java.util.*;
import java.io.*;

abstract class Trash {
  private double weight;
  Trash(double wt) { weight = wt; }
  abstract double value();
  double weight() { return weight; }
  // Sums the value of Trash in a bin:
  static void sumValue(Vector bin) {
    Enumeration e = bin.elements();
    double val = 0.0f;
    while(e.hasMoreElements()) {
      // One kind of RTTI:
      // A dynamically-checked cast
      Trash t = (Trash)e.nextElement();
      // Polymorphism in action:
      val += t.weight() * t.value();
      System.out.println(
        "weight of " +
        // Using RTTI to get type
        // information about the class:
        t.getClass().getName() +
        " = " + t.weight());
    }
    System.out.println("Total value = " + val);
  }
}

class Aluminum extends Trash {
  static double val  = 1.67f;
  Aluminum(double wt) { super(wt); }
  double value() { return val; }
  static void value(double newval) {
    val = newval;
  }
}

class Paper extends Trash {
  static double val = 0.10f;
  Paper(double wt) { super(wt); }
  double value() { return val; }
  static void value(double newval) {
    val = newval;
  }
}

class Glass extends Trash {
  static double val = 0.23f;
  Glass(double wt) { super(wt); }
  double value() { return val; }
  static void value(double newval) {
    val = newval;
  }
}

public class RecycleA { // 垃圾回收器设计 —— 第一版
  public static void main(String[] args) {
    Vector bin = new Vector();
    // Fill up the Trash bin:
    for(int i = 0; i < 30; i++)
      switch((int)(Math.random() * 3)) {
        case 0 :
          bin.addElement(new
            Aluminum(Math.random() * 100));
          break;
        case 1 :
          bin.addElement(new
            Paper(Math.random() * 100));
          break;
        case 2 :
          bin.addElement(new
            Glass(Math.random() * 100));
      }
    Vector 
      glassBin = new Vector(),
      paperBin = new Vector(),
      alBin = new Vector();
    Enumeration sorter = bin.elements();
    // Sort the Trash:
    while(sorter.hasMoreElements()) {
      Object t = sorter.nextElement();
      // RTTI to show class membership:
      if(t instanceof Aluminum)
        alBin.addElement(t);
      if(t instanceof Paper)
        paperBin.addElement(t);
      if(t instanceof Glass)
        glassBin.addElement(t);
    }
    Trash.sumValue(alBin);
    Trash.sumValue(paperBin);
    Trash.sumValue(glassBin);
    Trash.sumValue(bin);
  }
} ///:~

16.4 改进设计

16.4.1 “制作更多的对象” —— “若设计过于复杂，就制作更多的对象”
首先考虑Trash对象首次创建的地方，这是 main() 里的一个switch语句：
    for(int i = 0; i < 30; i++)
      switch((int)(Math.random() * 3)) {
        case 0 :
          bin.addElement(new
            Aluminum(Math.random() * 100));
          break;
        case 1 :
          bin.addElement(new
            Paper(Math.random() * 100));
          break;
        case 2 :
          bin.addElement(new
            Glass(Math.random() * 100));
      }
在《Design Patterns》中，它被粗略地称呼为“创建范式”。
要在这里应用的特殊范式是Factory方法的一种变体。
在这里，Factory方法属于Trash的一名static（静态）成员
class Info {
  int type;
  // Must change this to add another type:
  static final int MAX_NUM = 4;
  double data;
  Info(int typeNum, double dat) {
    type = typeNum % MAX_NUM;
    data = dat;
  }
}
用于这个简单示例的 factory() 方法如下：
  static Trash factory(Info i) {
    switch(i.type) {
      default: // To quiet the compiler
      case 0:
        return new Aluminum(i.data);
      case 1:
        return new Paper(i.data);
      case 2:
        return new Glass(i.data);
      // Two lines here:
      case 3: 
        return new Cardboard(i.data);
    }
  }
新对象在main()中的创建现在变得非常简单和清爽：
    for(int i = 0; i < 30; i++)
      bin.addElement(
        Trash.factory(
          new Info(
            (int)(Math.random() * Info.MAX_NUM),
            Math.random() * 100)));

......

16.5 抽象的应用 —— “如果必须做不雅的事情，至少应将其本地化到一个类里”

16.6 多重派遣

16.7 访问器范式

16.8 RTTI真的有害吗
由于RTTI常被滥用（让它查找系统中的每一种类型），会造成代码的扩展能力大打折扣——添加一种新类型时，必须找出使用了RTTI的所有代码。
即使仅遗漏了其中的一个，也不能从编译器那里得到任何帮助。

16.9 总结

16.10 练习
(1) 将SingletonPattern.java作为起点，创建一个类，用它管理自己固定数量的对象。

(2) 为TrashVisitor.java添加一个名为Plastic（塑料）的类。

(3) 为DynaTrash.java同样添加一个Plastic（塑料）类。

