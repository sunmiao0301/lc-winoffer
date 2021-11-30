我们通常需要将一个程序转换成多个独立运行的子任务。
象这样的每个子任务都叫作一个“线程”（Thread）。
编写程序时，可将每个线程都想象成独立运行，而且都有自己的专用CPU。
一些基础机制实际会为我们自动分割CPU的时间。

事实上，多线程最主要的一个用途就是构建一个“反应灵敏”的用户界面。

14.1.1 从线程继承
1）从Thread类继承
为创建一个线程，最简单的方法就是从Thread类继承。
这个类包含了创建和运行线程所需的一切东西。
Thread最重要的方法是run()。
但为了使用run()，必须对其进行过载或者覆盖，使其能充分按自己的吩咐行事
因此，run()属于那些会与程序中的其他线程“并发”执行代码。

public class SimpleThread extends Thread {
  private int countDown = 5;
  private int threadNumber;
  private static int threadCount = 0;
  public SimpleThread() {
    threadNumber = ++threadCount;
    System.out.println("Making " + threadNumber);
  }
  public void run() {
    while(true) {
      System.out.println("Thread " + 
        threadNumber + "(" + countDown + ")");
      if(--countDown == 0) return;
    }
  }
  public static void main(String[] args) {
    for(int i = 0; i < 5; i++)
    /*
    Thread包含了一个特殊的方法，叫作start()，它的作用是对线程进行特殊的初始化，然后调用run()。
    */
      new SimpleThread().start();
    System.out.println("All Threads Started");
  }
} ///:~
这个程序的 某一次 运行结果为：
Making 1
Making 2
Making 3
Making 4
Making 5
Thread 1(5)
Thread 1(4)
Thread 1(3)
Thread 1(2)
Thread 2(5)
Thread 2(4)
Thread 2(3)
Thread 2(2)
Thread 2(1)
Thread 1(1)
All Threads Started
Thread 3(5)
Thread 4(5)
Thread 4(4)
Thread 4(3)
Thread 4(2)
Thread 4(1)
Thread 5(5)
Thread 5(4)
Thread 5(3)
Thread 5(2)
Thread 5(1)
Thread 3(4)
Thread 3(3)
Thread 3(2)
Thread 3(1)
可以看出线程并不是按照创建时的顺序运行的，事实上，CPU处理现有线程集的顺序是不确定的，除非用Thread的setPriority()方法人为干预

14.1.2 针对用户界面的多线程 —— unseen

1.用内部类改善代码

14.1.3 用主类合并线程 —— unclare
若某样东西有一个Runnable接口，实际只是意味着它有一个run()方法，
但不存在与之相关的任何特殊东西——它不具有任何天生的线程处理能力，这与那些从Thread继承的类是不同的。
所以为了从一个Runnable对象产生线程，必须单独创建一个线程，并为其传递Runnable对象；
可为其使用一个特殊的构建器，并令其采用一个Runnable作为自己的参数使用。随后便可为那个线程调用start()，如下所示：
selfThread.start();
Runnable接口最大的一个优点是所有东西都从属于相同的类。
若需访问什么东西，只需简单地访问它即可，不需要涉及一个独立的对象。
但为这种便利也是要付出代价的——只可为那个特定的对象运行单独一个线程（尽管可创建那种类型的多个对象，或者在不同的类里创建其他对象）。

14.2 共享有限的资源
为了让多线程机制能够正常运转，需要采取一些措施来防止两个线程访问相同的资源
为防止出现这样的冲突，只需在线程使用一个资源时为其加锁即可。
访问资源的第一个线程会其加上锁以后，其他线程便不能再使用那个资源，除非被解锁。

14.2.2 Java如何共享资源
对一种特殊的资源——对象中的内存——Java提供了内建的机制来防止它们的冲突。
由于我们通常将数据元素设为从属于private（私有）类，然后只通过方法访问那些内存，
所以只需将一个特定的方法设为synchronized（同步的），便可有效地防止冲突。
下面列出简单的synchronized方法：
/*
在任何时刻，只可有一个线程调用特定对象的一个synchronized方法（尽管那个线程可以调用多个对象的同步方法）。
*/
synchronized void f() { /* ... */ }
详细解释的话，Java中每个对象都包含一把锁（也称“监视器”）
当调用这个对象的任意一个 synchronized 方法时 对象就被锁定 该对象的其他 synchronized 方法就无法被调用
！！！注意：对于访问某个关键共享资源的所有方法，都必须把它们设为synchronized，否则就不能正常地工作。有的上锁有的不上锁就等于都没上锁

14.3 堵塞
线程可以有四种状态：
1）New 新 ：已创建、未启动、所以不可运行
2）Runnable 可运行 ： 已创建、已启动、可运行，具体来说就是可能正在运行或是不在运行
3）Dead 死 ： 从 run() 方法中返回后，线程便已“Dead”
4）Blocker 堵塞 ： 可运行，但是某种东西阻碍了它。
若线程处于堵塞状态，调度机制可以简单地跳过它，不给它分配任何CPU时间。除非线程再次进入“可运行”状态，否则不会采取任何操作。
  线程被堵塞可能是由下述五方面的原因造成的：
  1）调用Sleep 使线程进入睡眠
  2）suspend() 暂停线程的执行 —— 直到 resume() 才会返回 Runnable 状态
  3）wait()暂停线程的执行 —— 直到 notify() 或者 notifyAll() 才会返回 Runnable 状态
  4）线程在等待一些IO操作完成
  5）线程需要使用的方法对应的对象处于被锁
----to be continued

14.3.2 死锁
就语言本身来说，尚未直接提供防止死锁的帮助措施，需要我们通过谨慎的设计来避免

14.4 优先级
可用 getPriority() 方法读取一个线程的优先级，并用 setPriority() 改变它。在下面这个程序片中，
----所有线程都隶属于一个线程组，线程组又隶属于其他线程组，所有线程组最终都隶属于系统线程组（父）

14.5 回顾 runnable
在本章早些时候，我曾建议大家在将一个程序片或主Frame当作Runnable的实现形式之前，一定要好好地想一想。
若采用那种方式，就只能在自己的程序中使用其中的一个线程。
当然，如果必须从一个类继承，而且想使类具有线程处理能力，则Runnable是一种正确的方案。

14.5.1 过多的线程

14.6 总结
线程另一个优点是它们用“轻度”线程 取代了“重度”进程。
由于一个进程内的所有线程共享相同的内存空间，所以“轻度”场景切换只改变程序的执行和本地变量。
而在“重度”场景切换时，一个进程的改变要求必须完整地交换内存空间。

14.7 练习

(1) 从Thread继承一个类，并（过载）覆盖run()方法。在run()内，打印出一条消息，然后调用sleep()。重复三遍这些操作，然后从run()返回。在构建器中放置一条启动消息，并覆盖finalize()，打印一条关闭消息。创建一个独立的线程类，使它在run()内调用System.gc()和System.runFinalization()，并打印一条消息，表明调用成功。创建这两种类型的几个线程，然后运行它们，看看会发生什么。

(2) 修改Counter2.java，使线程成为一个内部类，而且不需要明确保存指向Counter2的一个。

(3) 修改Sharing2.java，在TwoCounter的run()方法内部添加一个synchronized（同步）块，而不是同步整个run()方法。

(4) 创建两个Thread子类，第一个的run()方法用于最开始的启动，并捕获第二个Thread对象的句柄，然后调用wait()。第二个类的run()应在过几秒后为第一个线程调用modifyAll()，使第一个线程能打印出一条消息。

(5) 在Ticker2内的Counter5.java中，删除yield()，并解释一下结果。用一个sleep()换掉yield()，再解释一下结果。

(6) 在ThreadGroup1.java中，将对sys.suspend()的调用换成对线程组的一个wait()调用，令其等候2秒钟。为了保证获得正确的结果，必须在一个同步块内取得sys的对象锁。

(7) 修改Daemons.java，使main()有一个sleep()，而不是一个readLine()。实验不同的睡眠时间，看看会有什么发生。

(8) 到第7章（中间部分）找到那个GreenhouseControls.java例子，它应该由三个文件构成。在Event.java中，Event类建立在对时间的监视基础上。修改这个Event，使其成为一个线程。然后修改其余的设计，使它们能与新的、以线程为基础的Event正常协作。



