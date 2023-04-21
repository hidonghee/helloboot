package tobyspring.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary // 우선적으로 주입되는 빈
public class HelloDecorator implements HelloService{
    private final HelloService helloService;

    public HelloDecorator(HelloService helloService){
        this.helloService = helloService;
    }
    @Override
    public String sayHello(String name){ // Primary 애노테이션으로 인해 HelloDecorator가 먼저 Controller에게 빈으로 전달 됨. 따라서
        return "*" +helloService.sayHello(name)+"*";
    }
}
