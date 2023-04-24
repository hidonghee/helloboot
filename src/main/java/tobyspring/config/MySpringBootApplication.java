package tobyspring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 런타임까지 애노테이션이 유지될 수 있도록
@Target(ElementType.TYPE) // class ,인터페이스, 내부클래스, enum 등 3가지 이상에게 부여할 때 TYPE으로 해준다.
@Configuration
@ComponentScan // 패키지 밑에 있는 component가 bean으로 등록되게 찾는다.
@EnableMyAutoConfiguration
public @interface MySpringBootApplication {
}
