Java的基本原理就是“形式错误的代码不会运行”

之所以需要违例差错控制 —— 是因为:
若每次调用一个方法时都进行全面、细致的错误检查，那么代码的可读性也可能大幅度降低。
并且按这种方式控制错误，那么在创建大型、健壮、易于维护的程序时，肯定会遇到不小的阻挠。

本章在于介绍了用于正确控制违例所需的代码，以及在某个方法遇到麻烦的时候，该如何生成自己的违例。

9.1 基本违例
违例控制的步骤：
1）像创建对象一样创建违例 new 一个在内存堆里
2）停止当前路径 然后违例控制对象接管一切 并找机会继续程序的执行 执行方法有两种
 2.1）尝试另外一条执行路径
 2.2）直接 简单的继续
示例：
作为产生违例的一个简单示例，大家可思考一个名为t的对象句柄。
并且是尚未初始化的句柄。那么其违例控制就是
if(t == null)
throw new NullPointerException(); // 需要在内存堆里new创建违例
这样便“掷”出了一个违例。
在当前场景中，它使我们能放弃进一步解决该问题的企图。该问题会被转移到其他更恰当的地方解决。
此外，
还有另外一种违例自变量的构造方法，需使用一个字串自变量，使我们能在违例里置入相关信息：
if(t == null)
throw new NullPointerException("t = null");

9.2 违例的捕获
在9.1中提到的是在产生违例过程中就退出，然后通过throw离开方法
然而不想离开方法，可以通过 try - catch 来捕获违例
try {
  // 可能产生违例的代码
} catch(Type1 id1) {
  // Handle exceptions of Type1
} catch(Type2 id2) {
  // Handle exceptions of Type2
} catch(Type3 id3) {
  // Handle exceptions of Type3
}

// etc...

1.中断和恢复

9.2.3 违例规范
Java中，需要在包含违例控制的方法的 声明 部分，使用关键字throws来通知客户这里存在违例控制
如下：
void f() throws tooBig, tooSmall, divZero { //...

若使用下述代码：
void f() [ // ...
它意味着不会从方法里“掷”出违例（除类型为RuntimeException的违例以外，它可能从任何地方掷出——稍后还会详细讲述）

9.2.4 捕获所有违例
我们可创建一个控制器，令其捕获所有类型的违例。
具体的做法是捕获基础类违例类型Exception
（也存在其他类型的基础违例，但Exception是适用于几乎所有编程活动的基础）
catch(Exception e) {
System.out.println("caught an exception");
}
正由于这里能捕获所有违例 所以实际使用时应该置于控制器末尾，防止跟随在后面的任何特殊违例控制器失效

9.2.5 重新掷出违例 —— unseen

PS 不必关心违例的清楚 违例也属于用new创建的、以内存堆为基础的对象 垃圾收集器会将其自动清除

9.3 标准Java违例

9.3.1 RuntimeException的特殊情况
编译器不强制要求违规捕获RuntimeException 所以可以像下面这样
//: NeverCaught.java
// Ignoring RuntimeExceptions

public class NeverCaught {
  static void f() {
    throw new RuntimeException("From f()");
  }
  static void g() {
    f();
  }
  public static void main(String[] args) {
    g();
  }
} ///:~
如此，运行时的输出就是：
java.lang.RuntimeException: From f()
at NeverCaught.f(NeverCaught.java:9)
at NeverCaught.g(NeverCaught.java:12)
at NeverCaught.main(NeverCaught.java:15)
答案就是：假若一个RuntimeException获得到达main()的所有途径，同时不被捕获，那么当程序退出时，会为那个违例调用printStackTrace()。

9.4 创建自己的违例 —— 通过extends
//: Inheriting.java
// Inheriting your own exceptions

class MyException extends Exception {
  public MyException() {}
  public MyException(String msg) {
    super(msg);
  }
}

public class Inheriting {
  public static void f() throws MyException {
    System.out.println(
      "Throwing MyException from f()");
    throw new MyException();
  }
  public static void g() throws MyException {
    System.out.println(
      "Throwing MyException from g()");
    throw new MyException("Originated in g()");
  }
  public static void main(String[] args) {
    try {
      f();
    } catch(MyException e) {
      e.printStackTrace();
    }
    try {
      g();
    } catch(MyException e) {
      e.printStackTrace();
    }
  }
} ///:~
该程序输出结果如下：
Throwing MyException from f()
MyException
        at Inheriting.f(Inheriting.java:16)
        at Inheriting.main(Inheriting.java:24)
Throwing MyException from g()
MyException: Originated in g()
        at Inheriting.g(Inheriting.java:20)
        at Inheriting.main(Inheriting.java:29)
可以看到，在从f()“掷”出的MyException违例中，缺乏详细的消息。 但是g()就有违例信息"Originated in g()" 

9.5 违例的限制 —— unclare
用于一个特定方法的“违例规范接口”可能在继承和覆盖时变得更“窄”，但它不会变得更“宽”——这与继承时的类接口规则是正好相反的。

9.6 用finally清除
最完整违例控制：
try {
// 要保卫的区域：
// 可能“掷”出A,B,或C的危险情况
} catch (A a1) {
// 控制器 A
} catch (B b1) {
// 控制器 B
} catch (C c1) {
// 控制器 C
} finally {
// 每次都会发生的情况
}
示例：
//: FinallyWorks.java
// The finally clause is always executed

public class FinallyWorks {
  static int count = 0;
  public static void main(String[] args) {
    while(true) {
      try {
        // post-increment is zero first time:
        if(count++ == 0)
          throw new Exception();
        System.out.println("No exception");
      } catch(Exception e) {
        System.out.println("Exception thrown");
      } finally {
        System.out.println("in finally clause");
        if(count == 2) break; // out of "while"
      }
    }
  }
} ///:~
运行结果：
Exception thrown
in finally clause
No exception
in finally clause
从两次来到了finally部分可以得出结论：无论是否“掷”出一个违例，finally从句都会执行。

9.6.1 用finally做什么 —— 在C++中，finally一般用于放置垃圾收集的作用
但是在Java中 不需要垃圾收集 但是仍有关键作用 
----“除将内存设回原始状态以外，若要设置另一些东西，finally就是必需的。例如，我们有时需要打开一个文件或者建立一个网络连接，或者在屏幕上画一些东西，甚至设置外部世界的一个开关，等等。如下例所示：”

9.6.2 缺点：丢失的违例 —— unclare

9.7 构建器 —— unseen

9.8 违例匹配
在违例 Exception 它的控制器 catch 之间，并不需要非常精确的匹配。一个衍生类对象可与基础类的一个控制器相配

9.8.1 违例准则

用违例做下面这些事情：

(1) 解决问题并再次调用造成违例的方法。

(2) 平息事态的发展，并在不重新尝试方法的前提下继续。

(3) 计算另一些结果，而不是希望方法产生的结果。

(4) 在当前环境中尽可能解决问题，以及将相同的违例重新“掷”出一个更高级的环境。

(5) 在当前环境中尽可能解决问题，以及将不同的违例重新“掷”出一个更高级的环境。

(6) 中止程序执行。

(7) 简化编码。若违例方案使事情变得更加复杂，那就会令人非常烦恼，不如不用。

(8) 使自己的库和程序变得更加安全。这既是一种“短期投资”（便于调试），也是一种“长期投资”（改善应用程序的健壮性）

9.9 总结
Java中，违例控制的目的是使用尽可能精简的代码创建大型、可靠的应用程序，同时排除程序里那些不能控制的错误。

9.10 练习
(1) 用main()创建一个类，令其掷出try块内的Exception类的一个对象。为Exception的构建器赋予一个字串参数。在catch从句内捕获违例，并打印出字串参数。添加一个finally从句，并打印一条消息，证明自己真正到达那里。
try{
 throw new Exception();
}catch(Exception e){
 System.out.println("Caught u!")
}finally{
 system.out.println("Has came.")
}

(2) 用extends关键字创建自己的违例类。为这个类写一个构建器，令其采用String参数，并随同String句柄把它保存到对象内。写一个方法，令其打印出保存下来的String。创建一个try-catch从句，练习实际操作新违例。

(3) 写一个类，并令一个方法掷出在练习2中创建的类型的一个违例。试着在没有违例规范的前提下编译它，观察编译器会报告什么。接着添加适当的违例规范。在一个try-catch从句中尝试自己的类以及它的违例。

(4) 在第5章，找到调用了Assert.java的两个程序，并修改它们，令其掷出自己的违例类型，而不是打印到System.err。该违例应是扩展了RuntimeException的一个内部类。


