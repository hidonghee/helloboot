package tobyspring.helloboot;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// 의존 오브젝트에서 고립시켜서 테스트도 가능함.
public class HelloControllerTest {
    @Test
    void hellController() {
        HelloController helloController = new HelloController(name -> name);
        String ret = helloController.hello("Test");

        Assertions.assertThat(ret).isEqualTo("Test");
    }
    @Test
    void failsHelloController(){
        HelloController helloController = new HelloController(name -> name);
        Assertions.assertThatThrownBy(()->{
            String ret = helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);
        // 빈 문자열을 넣을시 Null이 아니기 때문에 별도의 예외처리도 필요하다.
        // 따라서 컨트롤러에서 에러처리를 해서 문제 발생시 에러를 정의해주는게 좋다.
        Assertions.assertThatThrownBy(()->{
            String ret = helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
