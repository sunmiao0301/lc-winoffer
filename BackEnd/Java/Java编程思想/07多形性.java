“面向对象三大特性 封装（抽象） 继承 多态”

因为多个类型（从相同的基础类型中衍生出来）可被当作同一种类型对待。

7.1 上溯造型
//: Music.java 
// Inheritance & upcasting
package c07;

class Note {
  private int value;
  private Note(int val) { value = val; }
  public static final Note
    middleC = new Note(0), 
    cSharp = new Note(1),
    cFlat = new Note(2);
} // Etc.

class Instrument {
  public void play(Note n) {
    System.out.println("Instrument.play()");
  }
}

// Wind objects are instruments
// because they have the same interface:
class Wind extends Instrument {
  // Redefine interface method:
  public void play(Note n) {
    System.out.println("Wind.play()");
  }
}

public class Music {
  /*
  而且如果让tune()简单地取得一个Wind句柄，将其作为自己的自变量使用，似乎会更加简单、直观得多。
  但要注意：假如那样做，就需为系统内Instrument的每种类型写一个全新的tune()。
  假设按照前面的推论，加入Stringed（弦乐）和Brass（铜管）这两种Instrument（乐器）
  这样做当然行得通，但却存在一个极大的弊端：必须为每种新增的Instrument2类编写与类紧密相关的方法。
  这意味着第一次就要求多得多的编程量。
  
  ---但假如只写一个方法，将基础类作为自变量或参数使用，而不是使用那些特定的衍生类，岂不是会简单得多？
  ---也就是说，如果我们能不顾衍生类，只让自己的代码与基础类打交道，那么省下的工作量将是难以估计的。
  */
  public static void tune(Instrument i) { // 此处的tune()方法参数是 Instrument 而不是 Wind
    // ...
    i.play(Note.middleC);
  }
  public static void main(String[] args) {
    Wind flute = new Wind();
    tune(flute); // Upcasting
  }
} ///:~

7.2 深入理解多态 ———— 之 动态绑定
Java里绑定的所有方法都通过 后期绑定 
故而 具有多态
可用下面这个语句简单地表现出来：
Shape s = new Circle();
在这里，我们创建了Circle对象，并将结果句柄立即赋给一个Shape。
这表面看起来似乎属于错误操作（将一种类型分配给另一个），但实际是完全可行的——因为按照继承关系，Circle属于Shape的一种。
因此编译器认可上述语句，不会向我们提示一条出错消息。 当我们调用其中一个基础类方法时（已在衍生类里覆盖）：
s.draw();
同样地，大家也许认为会调用Shape的draw()，因为这毕竟是一个Shape句柄。
那么编译器怎样才能知道该做其他任何事情呢？
但此时实际调用的是Circle.draw()，因为后期绑定已经介入（多态）。

7.3 覆盖和过载
覆盖就是原先是1，现在变成2
过载是原先是1，现在再加个2

7.4 抽象类和方法
对于经典的例子：
乐器类 ———— 提琴类；钢琴类；吉他类
其中乐器类中有一个通用接口，这个乐器类其实就是一个“抽象类”（“抽象基础类”）
正对这个问题，还可以干脆做绝一些，让乐器类中所有的方法都显示错误消息，Java对此提供了一种机制“抽象方法”
abstract void X();
包含了抽象方法的类也必须指定成抽象类（ 用 abstract ）
但是抽象类中的方法不必都是抽象方法

7.5 接口
“interface”（接口）关键字使抽象的概念更深入了一层。我们可将其想象为一个“更纯的”抽象类。 
为创建一个接口，请使用interface关键字，而不要用class。
为了生成与一个特定的接口（或一组接口）相符的类，要使用implements（实现）关键字。
在implements的时候
来自接口的方法必须定义成public。否则的话，它们会默认为“友好的”，而且会限制我们在继承过程中对一个方法的访问——Java编译器不允许我们那样做。
上代码：
//: Music5.java
// Interfaces
import java.util.*;

interface Instrument5 {
  // Compile-time constant:
  int i = 5; // static & final
  // Cannot have method definitions:
  void play(); // Automatically public
  String what();
  void adjust();
}

class Wind5 implements Instrument5 {
  public void play() {
    System.out.println("Wind5.play()");
  }
  public String what() { return "Wind5"; }
  public void adjust() {}
}

  static void tuneAll(Instrument5[] e) {
    for(int i = 0; i < e.length; i++)
      tune(e[i]);
  }

  public static void main(String[] args) {
    Instrument5[] orchestra = new Instrument5[1];
    int i = 0;
    // Upcasting during addition to the array:
    orchestra[i++] = new Wind5();
    tuneAll(orchestra);
  }
} ///:~

7.5.1 Java的多重继承 —— 通过Java中的接口实现了C++中的多重继承 —— 或者说能上溯造型到多个基础类
既然有了抽象 为什么还需要接口？
答：Java中没有多重继承 但是可以合并多个接口 如下
//: Adventure.java
// Multiple interfaces
import java.util.*;

interface CanFight {
  void fight();
}

interface CanSwim {
  void swim();
}

/*
Hero将具体类ActionCharacter同接口CanFight，CanSwim以及CanFly合并起来。
按这种形式合并一个具体类与接口的时候，具体类必须首先出现，然后才是接口（否则编译器会报错）。
*/
class Hero extends ActionCharacter 
    implements CanFight, CanSwim, {
  public void swim() {}
}

public class Adventure {
  static void t(CanFight x) { x.fight(); }
  static void u(CanSwim x) { x.swim(); }
  public static void main(String[] args) {
    Hero i = new Hero();
    t(i); // Treat it as a CanFight
    u(i); // Treat it as a CanSwim
  }
} ///:~

7.5.2 通过继承拓展接口（或者说：对接口的继承）
！注意：这里可以多重继承了 但是只有接口可以被接口或者类多重继承 如
类1 extends 接口1， 接口2 {}
接口1 extends 接口1， 接口2 {}
其他都不行

7.5.3 常数分组 —— unseen

7.5.4 初始化接口中的字段 —— unseen

7.6 内部类 —— DIFFICULT
 
7.7 构建器和多形性 —— DIFFICULT

7.8 通过继承进行设计 —— DIFFICULT

7.8.2 下溯造型 与 运行期类型标识
为了保证下溯造型正确进行，Java对所有下溯造型进行“运行期类型标识RTTI”，如果有问题，就会得到ClassCastException（类造型违例）如下
//: RTTI.java
// Downcasting & Run-Time Type
// Identification (RTTI)
import java.util.*;

class Useful {
  public void f() {}
  public void g() {}
}

class MoreUseful extends Useful {
  public void f() {}
  public void g() {}
  public void u() {}
  public void v() {}
  public void w() {}
}

public class RTTI {
  public static void main(String[] args) {
    Useful[] x = {
      new Useful(),
      new MoreUseful()
    };
    x[0].f();
    x[1].g();
    // Compile-time: method not found in Useful:
    //! x[1].u();
    ((MoreUseful)x[0]).u(); // Exception thrown 下溯造型 出问题了 得到一个ClassCastException
    ((MoreUseful)x[1]).u(); // Downcast/RTTI 下溯造型 没问题
  }
} ///:~

7.9 总结
多态 —— 记住 Shape 和 Circle、Square 就是一种多态

编者按：为使用多形性乃至面向对象的技术，特别是在自己的程序中，必须将自己的编程视野扩展到不仅包括单独一个类的成员和消息，也要包括类与类之间的一致性以及它们的关系。尽管这要求学习时付出更多的精力，但却是非常值得的，因为只有这样才可真正有效地加快自己的编程速度、更好地组织代码、更容易做出包容面广的程序以及更易对自己的代码进行维护与扩展。

7.10 练习
(1) 创建Rodent（啮齿动物）:Mouse（老鼠）,Gerbil（鼹鼠）,Hamster（大颊鼠）等的一个继承分级结构。在基础类中，提供适用于所有Rodent的方法，并在衍生类中覆盖它们，从而根据不同类型的Rodent采取不同的行动。创建一个Rodent数组，在其中填充不同类型的Rodent，然后调用自己的基础类方法，看看会有什么情况发生。
class Rodent{
  void scream(){}
}
class Mouse extends Rodent{
  void scream(){
    System.out.println("laoooo");
  }
}
class Gerbil extends Rodent{
  void scream(){
    System.out.println("yannnn");
  }
}
class Hamster extends Rodent{
  void scream(){
    System.out.println("daaaaa");
  }
}
public Class Main{
  public static void main(String[] args){
    Rodent arr = new Rodent[3];
    arr[0] = new Mouse;
    arr[1] = new Gerbil();
    arr[2] = new Hamster();
    for(int i = 0; i < 3; i++){
      arr[i].scream();
    }
  }
}
(2) 修改练习1，使Rodent成为一个接口。
interface Rodent{
  void scream(){}
}
class Mouse implements Rodent{
  public void scream(){
    System.out.println("laoooo");
  }
}
class Gerbil implements Rodent{
  public void scream(){
    System.out.println("yannnn");
  }
}
class Hamster implements Rodent{
  public void scream(){
    System.out.println("daaaaa");
  }
}
public Class Main{
  public static void main(String[] args){
    Rodent arr = new Rodent[3];
    arr[0] = new Mouse();
    arr[1] = new Gerbil();
    arr[2] = new Hamster();
    for(int i = 0; i < 3; i++){
      arr[i].scream();
    }
  }
}

(3) 改正WindError.java中的问题。

(4) 在GreenhouseControls.java中，添加Event内部类，使其能打开和关闭风扇。



