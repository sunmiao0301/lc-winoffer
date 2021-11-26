在Java中，对类的复用首先要保证不会干扰原有的代码 所以有以下两种方式
1）合成：在新类中简单的创建原有类的对象
2）继承：它创建一个新类，将其作为现有类的一个“类型”。我们可以原样采取现有类的形式，并在其中加入新代码，同时不会对现有的类产生影响。

6.1 合成的语法 —— unseen

6.2 继承的语法
创建一个类时肯定会进行继承 因为若非如此 会从Java的标准根类Object中继承
下面是一个例子：
//: Detergent.java
// Inheritance syntax & properties
class Cleanser {
  private String s = new String("Cleanser");
  /*
  需要着重强调的是Cleanser中的所有类都是public属性。
  请记住，倘若省略所有访问指示符，则成员默认为“友好的”。
  这样一来，就只允许对包成员进行访问。
  在这个包内，任何人都可使用那些没有访问指示符的方法。
  例如，Detergent将不会遇到任何麻烦。
  然而，假设来自另外某个包的类准备继承Cleanser，它就只能访问那些public成员。

  所以在计划继承的时候，一个比较好的规则是将所有字段都设为private，并将所有方法都设为public
  */
  public void append(String a) { s += a; }
  public void dilute() { append(" dilute()"); }
  public void apply() { append(" apply()"); }
  public void scrub() { append(" scrub()"); }
  public void print() { System.out.println(s); }
  /*
  Cleanser和Detergent都包含了一个main()方法。
  我们可为自己的每个类都创建一个main()。
  通常建议大家象这样进行编写代码，使自己的测试代码能够封装到类内。
  即便在程序中含有数量众多的类，但对于在命令行请求的public类，只有main()才会得到调用。
  所以在这种情况下，当我们使用“java Detergent”的时候，调用的是Degergent.main()——即使Cleanser并非一个public类。
  采用这种将main()置入每个类的做法，可方便地为每个类都进行单元测试。
  而且在完成测试以后，毋需将main()删去；可把它保留下来，用于以后的测试。
  */
  public static void main(String[] args) {
    Cleanser x = new Cleanser();
    x.dilute(); x.apply(); x.scrub();
    x.print();
  }
}
public class Detergent extends Cleanser {
  // Change a method:
  public void scrub() {
    append(" Detergent.scrub()");
    /*
    在scrub()里，不可只是简单地发出对scrub()的调用。
    那样便造成了递归调用，我们不愿看到这一情况。
    为解决这个问题，Java提供了一个super关键字，它引用当前类已从中继承的一个“超类”（Superclass）。
    所以表达式super.scrub()调用的是方法scrub()的基础类版本。
    */
    super.scrub(); // Call base-class version 否则就会递归调用（调用Detergent里的scrub()
  }
  // Add methods to the interface:
  public void foam() { append(" foam()"); }
  // Test the new class:
  public static void main(String[] args) {
    Detergent x = new Detergent();
    x.dilute();
    x.apply();
    x.scrub();
    x.foam();
    x.print();
    System.out.println("Testing base class:");
    Cleanser.main(args);
  }
} ///:~

6.2.1 衍生类的构造函数中，Java会自动插入对基础类构建器的调用
但是如果基类的构造函数是含参的，那么在子类中，必须明确的编写对基类的调用代码，这是通过super关键字和适当的参数 来实现的，如下
//: Chess.java
// Inheritance, constructors and arguments
class Game {
  Game(int i) {
    System.out.println("Game constructor");
  }
}

class BoardGame extends Game {
  /*
  如果不调用BoardGames()内的基础类构建器，编译器就会报告自己找不到Games()形式的一个构建器。
  除此以外，在衍生类构建器中，对基础类构建器的调用是必须做的第一件事情（如操作失当，编译器会向我们指出）。
  */
  BoardGame(int i) {
    super(i); // 我还以为是super.Game(i); 我觉得这种super()应该是只能对于父类的构造函数生效吧
    System.out.println("BoardGame constructor");
  }
}

public class Chess extends BoardGame {
  Chess() {
    super(11);
    System.out.println("Chess constructor");
  }
  public static void main(String[] args) {
    Chess x = new Chess();
  }
} ///:~

6.3 合成与继承的结合 —— unseen

6.4 到底选择合成还是继承 

6.5 protected
//: Orc.java
// The protected keyword
import java.util.*;

class Villain {
  private int i;
  ...
  protected void set(int ii) { i = ii; }
}

public class Orc extends Villain {
  private int j;
  ...
  public void change(int x) { set(x); }
} ///:~
可以看到，change()拥有对set()的访问权限，因为它的属性是protected（受到保护的）。

6.6 积累开发
程序开发是一个不断递增或累计的过程，就像人们学习知识一样

6.7 上溯造型 —— 之继承中的体现 —— 上溯造型肯定是安全的
//: Wind.java
// Inheritance & upcasting
import java.util.*;

class Instrument {
  public void play() {}
  static void tune(Instrument i) {
    // ...
    i.play();
  }
}

// Wind objects are instruments
// because they have the same interface:
class Wind extends Instrument {
  public static void main(String[] args) {
    Wind flute = new Wind();
    /*
    我们一定要认识到一个Wind对象也是一个Instrument对象。
    在tune()中，代码适用于Instrument 以及 从Instrument衍生出来的任何东西。
    在这里，我们将从一个Wind句柄转换成一个Instrument句柄的行为叫作“上溯造型”。
    */
    Instrument.tune(flute); // Upcasting
  }
} ///:~

6.7.1  .1 再论合成和继承
为判断自己到底应该选用合成还是继承，一个最简单的办法就是考虑是否需要从新类上溯造型回基础类。若必须上溯，就需要继承。但如果不需要上溯造型，就应提醒自己防止继承的滥用。在下一章里（多形性），会向大家介绍必须进行上溯造型的一种场合。但只要记住经常问自己“我真的需要上溯造型吗”，对于合成还是继承的选择就不应该是个太大的问题。
 
6.8 final 关键字
final关键字的三种应用场合：
1）数据
  1.1）句柄
  1.2）基本数据类型
2）方法
  2.1）保持一个方法不被覆盖或是改写
  2.2）提高程序执行的效率（用的少）
3）类
  3.1）不希望这个类被继承
  ！注意当将一个类final后 其内所有的方法也都被final了 所以：
  可为final类内的一个方法添加final指示符，但这样做没有任何意义。
  
6.9 初始化和类装载 —— 类装载机制？

6.10 总结
下一章将讲述多态

6.11 练习
(1) 用默认构建器（空自变量列表）创建两个类：A和B，令它们自己声明自己。从A继承一个名为C的新类，并在C内创建一个成员B。不要为C创建一个构建器。创建类C的一个对象，并观察结果。

(2) 修改练习1，使A和B都有含有自变量的构建器，则不是采用默认构建器。为C写一个构建器，并在C的构建器中执行所有初始化工作。

(3) 使用文件Cartoon.java，将Cartoon类的构建器代码变成注释内容标注出去。解释会发生什么事情。

(4) 使用文件Chess.java，将Chess类的构建器代码作为注释标注出去。同样解释会发生什么。










