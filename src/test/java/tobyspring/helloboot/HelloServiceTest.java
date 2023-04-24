package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest{

}

@Retention(RetentionPolicy.RUNTIME) // 새로 만들려면 필요함
@Target({ElementType.ANNOTATION_TYPE,ElementType.METHOD}) //ANNOTATION_TYPE은 내가 만든 애노테이션을 또 내가 만든 애노테이션이 사용할 수 있도록 도와준다.
@Test
@interface UnitTest{

}


public class HelloServiceTest {
// 클래스를 불러와서 직접 테스트하는게 좋음
    // 이유는 실행시간도 짧고 서버를 빌드 할 필요도 없음
//    @Test
    @FastUnitTest
    void simpleHelloService(){
        SimpleHelloService helloService = new SimpleHelloService();

        String ret = helloService.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("Hello Test");
    }
    @UnitTest
    void helloDecorator(){
        HelloDecorator decorator = new HelloDecorator(name -> name);

        String ret = decorator.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}
