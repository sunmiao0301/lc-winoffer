1.1 抽象的进步

抽象出对象
不必细谈

1.2 对象的接口

类与对象
对象隶属于一个特定的类，对象小，类大

接口（Interface）—— 我们向对象发出的请求是通过他的 接口 定义的
Light It = new Light();//定义一个Light类对象It
It.on();//通过on()向对象发送信息

封装
通过 public private protected 来实现
public 不必说
private 是只有当前类的内部函数成员可以
protected 比private要多一个：继承的类也可以访问

复用
复用就是在现有类的基础上，新组织一个新类，
注意！新类的成员对象通常设置为 private 由此使得在不干扰客户代码的前提下，从容地修改成员

继承
通过extends实现，需要注意的是，继承之后的子类会随着父类的变化而变化
1）
通常，继承用于：意识到基础类不能满足我们的需求，所以为子类加更多的函数 这是一种最基本的继承用法
2）
如果不想在继承时，仅仅是增加新函数，还可以对子类继承得到的父类函数进行重写
“尽管使用的函数接口未变，但是其新版本具有不同的表现”

多态
多态的基石————上溯造型（向上转型
interface Animal{    //动物接口
    void play(){};
}
class Dog implements Animal{    //狗类
    void play(){
        System.out.println("Dog");
    }
}
class Cat implements Animal{    //猫类
    void play(){
        System.out.println("Cat");
    }
}
public class Test{
    void play(Animal a){    //参数为动物接口
        a.play();
    }
    public static void main(String[] args){
        Dog dog = new Dog();
        Cat cat = new Cat();
        play(dog);    //传入狗类
        play(cat);    //传入猫类
    }
}
很明显，这里的Test中的 play() 方法中没有说明 Animal a 是猫还是狗
但是程序可以在运行时确定这个 play() 得到的参数是cat还是dog 原因就是 “动态绑定”
Java中方法的绑定都是动态绑定，除非一个方法被声明为final.

/*
向上转型的好处在于
子类可以转成父类，
所以方法只需要以父类为参数，子类也都可以用，若是没有向上转型，那么有多少子类，就得写多少个函数

public static void dosleep(Human h) {
    h.sleep();
}

这里以父类为参数，调有时用子类作为参数，就是利用了向上转型。
这样使代码变得简洁。不然的话，如果 dosleep 以子类对象为参数，则有多少个子类就需要写多少个函数。
这也体现了 JAVA 的抽象编程思想。
*/
  
1.6.2 抽象的基础类和接口
abstract 抽象 不必多说

1.7 对象的创建和存在时间
C++中是将对象放置在堆栈或者静态存储区，但是这种方法在编写程序时必须知道对象的准确数量、存在时间、以及类型
另外一种方法是在内存堆（堆、内存堆）中动态的创建对象
说到这 就不得不提 垃圾收集器（用于自动寻找那些不再使用的对象、并将其自动清除）

1.7.1 集合与迭代器Iterator
Iterator属于一种对象，它可以把一种集合（无论是矢量、链表还是堆栈）抽象成一个简单的序列，然后帮助我们遍历序列而无需关心基础结构

1.7.2 单根结构
所有类是否都应该从单独的一个基础类继承而来？
答案是肯定的，这个基础类就叫做 Object 这种处理有着优点
也能方便我们实现一个垃圾收集器

1.7.3 下溯造型与模板/通用性
如下代码所示，衍生类上溯造型会丢失数据，但再下溯造型回来后这些数据还存在。下述代码也表示下溯造型并不一定是安全的。
class Useful {
    public void f() {}
}
class MoreUseful extends Useful {
    public void f() {}
    public void g() {}
}
public class RTTI {
    public static void main(String[] args) {
    Useful[] x = {
        new Useful(),
        new MoreUseful()   //上溯造型
    };
    ((MoreUseful)x[0]).g();    //下溯造型报错 Exception thrown
    ((MoreUseful)x[1]).g();    //下溯造型成功
 }
}

1.8 违例控制：解决错误

1.9 多线程
多线程常见的例子就是用户界面，利用线程，用户可按下一个按钮，然后程序立即做出响应，而不是让用户等待程序完成了当前任务以后才开始响应。
需要注意！多线程的共享资源问题（多个线程同时运行，并试图访问相同的资源）
为解决这个问题，通常要求程序员创建一个对象，用它代表一把锁，所有线程在访问那个资源时都必须检查这把锁

1.10 永久性

1.11 Java和Web（World Wide Web）
对于C/S系统，程序员需要分散任务的处理负担，一般可以考虑让客户机负担部分处理任务
但有时亦可分派给服务器所在地的其他机器，那些机器亦可叫做“中间件”（中间件也用于改进对系统的维护）
解决C/S问题的方案是：Web浏览器和客户端编程
客户端编程问题：
Java通过“程序片 —— Applet”巧妙地解决了客户端编程的问题
服务器端编程问题“包括基于Java的Web服务器，允许我们用Java进行所有服务器端编程，写出的程序就叫做“小服务程序Servlet”

1.12 分析与设计
OOP
没有计划 50%以上的项目都会失败

1.13 Java还是C++ （在本书附录 可以找到Java/C++比较）



