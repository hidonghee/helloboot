package tobyspring.helloboot.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public class ConditionalTest {
    @Test
    void conditional(){
        //true
        //ApplicationContextRunner의 용도는 테스트로 사용하는 클래스인데 특정한 설정 정보를 불러와서 테스트를 하기에 적합한 클래스
        //withUserConfiguration() 메서드를 사용해서 configuration 정보를 등록 시킬 수 있다.
        ApplicationContextRunner contextRunner = new ApplicationContextRunner();
        contextRunner.withUserConfiguration(Config1.class).run(context -> {
            Assertions.assertThat(context).hasSingleBean(MyBean.class);
            Assertions.assertThat(context).hasSingleBean(Config1.class);
        });

        //false
        new ApplicationContextRunner().withUserConfiguration(Config2.class).run(context -> {
            Assertions.assertThat(context).doesNotHaveBean(MyBean.class);
            Assertions.assertThat(context).doesNotHaveBean(Config1.class);
        });
    }
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional{
        boolean value();
    }

    @Configuration
    @BooleanConditional(true)
    static class Config1{
        @Bean
        MyBean myBean(){
            return new MyBean();
        }
    }

    @Configuration
    @BooleanConditional(false)
    static class Config2{
        @Bean
        MyBean myBean(){
            return new MyBean();
        }
    }
    static class MyBean{}

     static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String,Object> annotationAttributes= metadata.getAnnotationAttributes(BooleanConditional.class.getName());// 애노테이션 속성값을 가져옴
            Boolean value = (Boolean) annotationAttributes.get("value");
            return value;
        }
    }

}
