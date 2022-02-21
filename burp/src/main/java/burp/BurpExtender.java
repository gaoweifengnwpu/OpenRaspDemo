package burp;

import burp.ui.SendExpGui;


import java.awt.*;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import burp.ui.Tools;

//IHttpListener
//
//        该类是用来注册HTTP监听器，然后对获取到的请求或响应包进行处理，
//        有个processHttpMessage的方法用于对请求和响应的数据包进行自定义操作，
//        该方法在发送请求之前和接收响应之后会被调用
public class BurpExtender implements IBurpExtender, ITab, IHttpListener {

    private IBurpExtenderCallbacks callbacks;
    private SendExpGui send;
    // obtain our output and error streams
    PrintWriter stdout;
    PrintWriter stderr;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        this.stdout = new PrintWriter(callbacks.getStdout(), true);
        this.stderr = new PrintWriter(callbacks.getStderr(), true);
        //插件名称
        callbacks.setExtensionName("高尉峰");
        send = new SendExpGui(this);
        callbacks.addSuiteTab(this);
        callbacks.registerHttpListener(this);
    }
    // 指示了发起请求或收到响应的 Burp 工具的 ID，所有的 toolFlag 定义在 IBurpExtenderCallbacks 接口中。
//    int toolFlag

    // 指示该消息是请求消息（值为True）还是响应消息（值为False）
//    messageIsRequest

    // 被处理的消息的详细信息，是一个 IHttpRequestResponse 对象
//            messageInfo
    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest,
                                   IHttpRequestResponse messageInfo) {
//        #获取请求包的数据
//                resquest = messageInfo.getRequest()
//        analyzedRequest = self._helpers.analyzeRequest(resquest)
//        request_header = analyzedRequest.getHeaders()
//        request_bodys = resquest[analyzedRequest.getBodyOffset():].tostring()
        // only process requests
        if (messageIsRequest) {
            IHttpService httpService = messageInfo.getHttpService();
            if (httpService.getPort() == 3111) {
                byte[] request = messageInfo.getRequest();
                // 按指定模式在字符串查找
                String line1 = new String(request);
//                String line1 = "GET / HTTP/1.1\n" +
//                        "Host: 192.30.3.158:3111\n" +
//                        "Cookie: d2admin-1.20.1-lang=zh-chs\n";
                //去掉左右换行字符
                String line = Tools.trimN(line1);

//                System.out.println(line);
                String pattern = "(.*)\r\n(.*)";
                // 创建 Pattern 对象
                Pattern r = Pattern.compile(pattern);

                // 现在创建 matcher 对象
                Matcher m = r.matcher(line);
                if (m.find()) {
                    stdout.println(m);
                    send.appendOutput(m.group(2)+" "+m.group(1));
                } else {
                    System.out.println("NO MATCH");
                }


            }
        }
    }

    @Override
    public String getTabCaption() {
        return "Api";
    }

    @Override
    public Component getUiComponent() {
        return send.$$$getRootComponent$$$();
    }
}
