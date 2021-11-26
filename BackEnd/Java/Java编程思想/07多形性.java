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










