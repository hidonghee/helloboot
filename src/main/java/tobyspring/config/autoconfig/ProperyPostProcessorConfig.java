package tobyspring.config.autoconfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.Environment;
import tobyspring.config.MyAutoConfiguration;
import tobyspring.config.MyConfigurationProperties;

import java.util.Map;

@MyAutoConfiguration
public class ProperyPostProcessorConfig {
    @Bean
    BeanPostProcessor propertyPostProcessor(Environment environment){
        return new BeanPostProcessor() {
            @Override // 해당 함수는 빈 오브젝트 초기화가 끝난 다음에 해당 함수의 작업을 진행해달라는 의미.
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                // 특정 클래스에 해당 애노테이션을 붙이고 있으면 그 클래스를 돌려달라
                MyConfigurationProperties annotation = AnnotationUtils.findAnnotation(bean.getClass(), MyConfigurationProperties.class);
                if (annotation == null) return bean;

                Map<String,Object> attrs = AnnotationUtils.getAnnotationAttributes(annotation);
                String prefix = (String)attrs.get("prefix");
                //properties를 가져와서 MyConfigurationProperties애노테이션이 붙은 클래스에 바인딩
                return Binder.get(environment).bindOrCreate(prefix,bean.getClass());
            }
        };

    }
}
