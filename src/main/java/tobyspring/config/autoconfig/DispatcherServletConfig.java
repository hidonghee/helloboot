package tobyspring.config.autoconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import tobyspring.config.MyAutoConfiguration;

//@Configuration
@MyAutoConfiguration
public class DispatcherServletConfig {
    @Bean
    public DispatcherServlet dispatcherServlet(){
        return new DispatcherServlet();
    }

}
