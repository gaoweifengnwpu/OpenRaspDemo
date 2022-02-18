
Java Agent 支持两种方式进行加载：

实现 premain 方法，在启动时进行加载 （该特性在 jdk 1.5 之后才有）
实现 agentmain 方法，在启动后进行加载 （该特性在 jdk 1.6 之后才有）

上面介绍的 premain 方法是在 JDK 1.5中提供的，所以在 jdk 1.5 的时候开发者只能在 main 加载之前添加手脚，但是很多时候例如我们内存马注入的情况都是处于 JVM 已运行了的情况，所以上面的方法就不是很有用，不过在 jdk 1.6 中实现了attach-on-demand（按需附着），我们可以使用 Attach API 动态加载 agent ，然而 Attach API 在 tool.jar 中，jvm 启动时是默认不加载该依赖的，需要我们在 classpath 中额外进行指定
![image](https://user-images.githubusercontent.com/35843926/154647583-627f55d2-c737-4bf5-91bf-d4211f4d1bcc.png)
agent打包
jar cvfm AgentMain.jar agentmain.mf AgentMain.class DefineTransformer.class 
