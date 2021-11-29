13.1 为何要用AWT？
为什么还要学习使用老的AWT呢？
原因很简单，因为它的存在是个事实。就目前来说，这个事实对我们来说显得有些不利，它涉及到面向对象库设计的一个宗旨：
一旦我们在库中公布一个组件，就再不能去掉它。如去掉它，就会损害别人已存在的代码。

13.2 基本程序片

13.3 制作按钮

13.4 捕获事件

13.5 文本字段

13.6 文本区域

13.7 标签

13.8 复选框

13.9 单选钮

13.10 下拉列表

13.11 列表框

13.11.1 handleEvent()
  
13.12 布局的控制 
13.12.1 FlowLayout
13.12.2 BorderLayout
13.12.3 GridLayout
13.12.4 CardLayout
13.12.5 GridBagLayout

13.13 action的替代品

13.14 程序片的局限

13.14.1 程序片的优点

13.15 视窗化应用
13.15.1 菜单
13.15.2 对话框

13.16 新型AWT
13.16.1 新的事件模型
13.16.2 事件和接收者类型

......
13.20 总结 
对于AWT而言，Java 1.1到Java 1.2最大的改变就是Java中所有的库。
Java 1.0版的AWT曾作为目前见过的最糟糕的一个设计被彻底地批评，并且当它允许我们在创建小巧精致的程序时，产生的GUI“在所有的平台上都同样的平庸”。
它与在特殊平台上本地应用程序开发工具相比也是受到限制的，笨拙的并且也是不友好的。
当Java 1.1版纳入新的事件模型和Java Beans时，平台被设置——现在它可以被拖放到可视化的应用程序构建工具中，创建GUI组件。
另外，事件模型的设计和Bean无疑对轻松的编程和可维护的代码都非常的在意（这些在Java 1.0 AWT中不那么的明显）。
但直至GUI组件－JFC/Swing类－显示工作结束它才这样。对于Swing组件而言，交叉平台GUI编程可以变成一种有教育意义的经验。 
现在，唯一的情况是缺乏应用程序构建工具，并且这就是真正的变革的存在之处。
微软的Visual Basic和Visual C++需要它们的应用程序构建工具，同样的是Borland的Delphi和C++构建器。
如果我们需要应用程序构建工具变得更好，我们不得不交叉我们的指针并且希望自动授权机会给我们所需要的。
Java是一个开放的环境，因此不但考虑到同其它的应用程序构建环境竞争，而且Java还促进它们的发展。
这些工具被认真地使用，它们必须支持Java Beans。
这意味着一个平等的应用领域：如果一个更好的应用程序构建工具出现，我们不需要去约束它就可以使用——我们可以采用并移动到新的工具上工作即可，这会提高我们的工作效率。
这种竞争的环境对应用程序构建工具来说从未出现过，这种竞争能真正提高程序设计者的工作效率。
  
13.21 练习 
(1)创建一个有文字字段和三个按钮的程序片。当我们按下每个按钮时，使不同的文字显示在文字段中。

(2)增加一个复选框到练习1创建的程序中，捕捉事件，并插入不同的文字到文字字段中。

(3)创建一个程序片并增加所有导致action()被调用的组件，然后捕捉他们的事件并在文字字段中为每个组件显示一个特定的消息。

(4)增加可以被handleEvent()方法测试事件的组件到练习3中。过载handleEvent()并在文字字段中为每个组件显示特定的消息。

(5)创建一个有一个按钮和一个TextField的程序片。编写一个handleEvent()，以便如果按钮有焦点，输入字符到将显示的TextField中。 

(6)创建一个应用程序并将本章所有的组件增加主要的帧，包括菜单和对话框。 

(7)修改TextNew.java，以便字母在t2中保持输入时的样子，取代自动变成大写。

(8)修改CardLayout1.java以便它使用Java 1.1的事件模型。 

(9)增加Frog.class到本章出现的清单文件中并运行jar以创建一个包括Frog和BangBean的JAR文件。现在从SUN公司处下载并安装BDK或者使用我们自己的可激活Bean的程序构建工具并增加JAR文件到我们的环境中，因此我们可以测试两个Bean。 

(10)创建我们自己的包括两个属性：一个布尔值为“on”，另一个为整型“level”，称为Valve的Java Bean。创建一个清单文件，利用jar打包我们的Bean，然后读入它到beanbox或到我们自己的激活程序构建工具里，因此我们可以测试它。 

(11)修改Menus.java，以便它处理多级菜单。这要假设读者已经熟悉了HTML的基础知识。但那些东西并不难理解，而且有一些书和资料可供参考。
