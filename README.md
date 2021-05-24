# OpenRaspDemo
javaagent是一个JVM的“插件”。 在java运行命令中 javaagent是一个参数，用来指定agent。

能做什么
可以在加载class文件之前进行拦截并把字节码做修改。
可以在运行期对已加载类的字节码做变更，但是这种情况下会有很多的限制。
还有其他一些小众的功能
获取所有已经加载过的类
获取所有已经初始化过的类（执行过 clinit 方法，是上面的一个子集）
获取某个对象的大小
将某个jar加入到bootstrap classpath里作为高优先级被bootstrapClassloader 加载
将某个jar加入到classpath里供AppClassloard去加载
设置某些native方法的前缀，主要在查找native方法的时候做规则匹配
总的来说可以让JVM按照我们的预期逻辑去执行。

最主要的也是使用最广的功能就是对字节码的修改。通过对字节码的修改我们就可以实现对JAVA底层源码的重写，也正好可以满足我之前的需求。 我们还可以做：

完全非侵入式的进行代码埋点，进行系统监控
修改JAVA底层源码，进行JVM自定义
实现AOP动态代理


-javaagent:D:\OpenRaspDemo\target\agent.jar
