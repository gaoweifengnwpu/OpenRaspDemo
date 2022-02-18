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


#openrasp官方文档
https://rasp.baidu.com/doc/install/testcase.html

安装管理后台
安装数据库
目前，我们使用了 ElasticSearch 和 MongoDB 两种数据库。前者用来存储报警和统计信息，后者用来存储应用、账号密码等信息。

目前我们对数据库的要求是，

MongoDB 版本大于等于 3.6
ElasticSearch 版本大于等于 5.6，小于 7.0 (ES 7.X 变化较大，暂无计划支持)
具体如何安装数据库，这里不在赘述。
然后，在终端里执行如下命令，启动后台服务器:

./rasp-cloud -d

最后，在浏览器里打开 http://your-ip:8086，登录管理后台。其中用户名固定为 openrasp，初始密码为 admin@123。如果不能访问，请检查防火墙设置，或者检查 logs/api/agent-cloud.log 下面的错误信息。

登录成功后，请根据 管理后台 - 添加主机 文档，了解如何添加第一台主机。
高级配置
后台负载均衡
在实际场景中，我们需要部署多个服务器，才能处理来自数十万主机的连接。因此，我们将管理后台拆分为 Agent 接口服务器 (AgentServer) 和 前端服务器 (PanelServer)。前者用于跟agent通信，比如心跳、日志上传，可以部署多个；后者用于展示数据、定时报警、修改配置，用户可访问这个后台来查看报警。后者用于发送报警邮件，部署多个会有重复报警的问题，所以目前只能部署一个。

在百度，我们会启动一个前端服务器，

./rasp-cloud -type=panel -d
然后在不同的机房，分别启动一个 agent 服务器（使用同一份 conf/app.conf 配置文件即可）

./rasp-cloud -type=agent -d
服务器部署完成后，请根据 管理后台 - 设置后台信息 文档，将所有的Agent服务器填入。之后就可以在 添加主机 界面上，生成自动安装命令了。

容量说明: 目前，在2核4GB的主机上，按照3分钟一个心跳计算，大概单台机器可容纳 1000个 客户端。

ElasticSearch 负载均衡
在使用ES集群时，EsAddr 填写一个服务器的地址即可。我们自动会调用 /_nodes/ 接口获取全部服务器信息并轮询，以避免单点问题。

agent服务器安装
安装jdk
安装tomcat
下载 rasp-java.tar.gz 或者 rasp-java.zip 并解压缩。之后进入到解压后的目录中
如果你要开启远程管理，请先参考 管理后台 - 添加主机 文档，找到 app_id/app_secret/backend_url 三个关键参数，然后执行如下命令
java -jar RaspInstall.jar -install <tomcat_root> -backendurl http://XXX -appsecret XXX -appid XXX
然后重启tomcat容器
![image](https://user-images.githubusercontent.com/35843926/119291517-27d4e980-bc81-11eb-9511-f634cec92d27.png)
![image](https://user-images.githubusercontent.com/35843926/119291589-4a670280-bc81-11eb-9819-b0435788a59d.png)
![image](https://user-images.githubusercontent.com/35843926/119291795-ab8ed600-bc81-11eb-8f7f-57afdc67947e.png)

