package tobyspring.helloboot;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtendWith(SpringExtension.class) // 스프링 컨텍스트를 이용하는 스프링 컨테이너 테스트가 가능
@ContextConfiguration(classes = HellobootApplication.class)// 모든 빈 구성정보를 끌어올 수 있음 (자동 구성 빈, 사용자 구성 빈)
@TestPropertySource("classpath:/application.properties") // Test환경에서 spring boot의 application.properties를 사용하기 위함.
/*// 특정 트랜잭션을 DB연결에 대한 독립적으로 만들어서 DB 테스트스 다른 테스트의 영향을 받지 않기 위해 사용함.
즉, 한 트랜잭션과 DB 또 다른 트랜잭션과 DB는 완전 다른 것.*/
@Transactional
public @interface HellobootTest {
}
