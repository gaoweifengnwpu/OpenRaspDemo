package burp;

import burp.ui.SendExpGui;


import java.awt.*;
import java.io.PrintWriter;


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

    @Override
    public void processHttpMessage(int toolFlag, boolean messageIsRequest,
                                   IHttpRequestResponse messageInfo) {
        // only process requests
        if (messageIsRequest) {
            IHttpService httpService = messageInfo.getHttpService();
            send.appendOutput(httpService.getProtocol());
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
