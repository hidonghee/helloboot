package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HellobootApplication {

	public static void main(String[] args) {
		GenericApplicationContext applicationContext = new GenericApplicationContext(); //spring 컨테이너
		applicationContext.registerBean(HelloController.class); // bean등록
		applicationContext.registerBean(SimpleHelloService.class);
		applicationContext.refresh(); // 컨테이너를 을 초기화

		ServletWebServerFactory serverFactory=new TomcatServletWebServerFactory(); //톰캣이나 제티 등 다양한 서블릿 컨테이너를 추상화했기 떄문에 선택해서 사용가능
		WebServer webServer=serverFactory.getWebServer(servletContext -> { // Http Servlet를 상속받는 익명클래스 => serveletContext
			servletContext.addServlet("FrontConroller", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					//인증, 보안, 다국어, 공통 기능
					if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
						String name = req.getParameter("name");
						HelloController helloController=applicationContext.getBean(HelloController.class);
						String ret = helloController.hello(name);
						//응답 만들기
						resp.setStatus(HttpStatus.OK.value());
						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println("Hello Servlet" + "\t" + name);
					}
					else if(req.getRequestURI().equals("/user")){
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
					else {
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
				}
			}).addMapping("/*");
		});
		webServer.start();
	}

}
