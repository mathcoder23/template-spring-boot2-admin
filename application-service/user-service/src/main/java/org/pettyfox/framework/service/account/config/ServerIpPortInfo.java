package org.pettyfox.framework.service.account.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 服务器本机ip和端口号
 * @author Petty Fox
 */
@Component
public class ServerIpPortInfo implements ApplicationListener<WebServerInitializedEvent> {
    public int getServerPort() {
        return serverPort;
    }

    private int serverPort;

    public String getUrl() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        assert address != null;
        return "http://"+address.getHostAddress()+":"+this.serverPort;
    }

    public String getHost() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return address.getHostAddress();
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        serverPort = event.getWebServer().getPort();
    }
}