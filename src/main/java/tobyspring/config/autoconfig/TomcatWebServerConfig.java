package tobyspring.config.autoconfig;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.*;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
//@Import(ServerProperties.class) // ConditionalMyOnClass에 의해 선택되면 import를 수행하고 해당클래스는 MyConfigurationProperties에 의해 빈 초기화 후 조작이 들어가서 속성 값을 정의한다.
@EnableMyConfigurationProperties(ServerProperties.class)
public class TomcatWebServerConfig {

    @Bean("tomcatWebServerFactory")
    @ConditionalOnMissingBean // 이미 사용자 구성정보에서 설정한 서버 펙토리 관련 빈이 먼저 등록됨.
    // 따라서 사용자 구성정보에서 servlet 컨테이너의 정의가 없으면 현재 이 위치의 빈이 올라옴.
    public ServletWebServerFactory servletWebServerFactory(ServerProperties properties){
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();

        factory.setContextPath(properties.getContextPath()); // 웹 페이지의 최상위 디렉터리가 붙음
        factory.setPort(properties.getPort());

        return factory;
    }

}
