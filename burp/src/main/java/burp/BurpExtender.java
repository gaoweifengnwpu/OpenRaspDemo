package burp;

import burp.ui.SendExpGui;


import java.awt.*;
import java.io.PrintWriter;

//IHttpListener
//
//        该类是用来注册HTTP监听器，然后对获取到的请求或响应包进行处理，
//        有个processHttpMessage的方法用于对请求和响应的数据包进行自定义操作，
//        该方法在发送请求之前和接收响应之后会被调用
public class BurpExtender implements IBurpExtender, ITab, IHttpListener {

    private IBurpExtenderCallbacks callbacks;
    public PrintWriter stdout;
    private SendExpGui send;

    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks) {
        this.callbacks = callbacks;
        //插件名称
        callbacks.setExtensionName("gwf");
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
                send.appendOutput(new String(request));

            }
        }
    }

    @Override
    public String getTabCaption() {
        return "gwf1";
    }

    @Override
    public Component getUiComponent() {
        return send.$$$getRootComponent$$$();
    }
}
