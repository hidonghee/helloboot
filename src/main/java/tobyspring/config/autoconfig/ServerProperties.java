package tobyspring.config.autoconfig;

import org.springframework.stereotype.Component;
import tobyspring.config.MyConfigurationProperties;


@MyConfigurationProperties(prefix = "server") //prefix를 통해서 server.port, server.contextPath 로 설정해서 명확히 구분가능
public class ServerProperties {
    private String contextPath;
    private int port;

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public int getPort() {
        return port;
    }
}
