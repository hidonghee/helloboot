package tobyspring.helloboot;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HelloApiTest {
    @Test//http -v ":8080/hello?name=Spring" => httpie 대신
    void helloApi(){

        TestRestTemplate rest = new TestRestTemplate();
        ResponseEntity<String> res= rest.getForEntity("http://localhost:8080/hello?name={name}",String.class,"Spring"); //메시지 바디를 String 클래스로 바인딩
        // 응답 검증
        // status 200
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        // header(content-type) text/plain
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        //body Hello Spring
        Assertions.assertThat(res.getBody()).isEqualTo("Hello Spring");
    }
    @Test
    void failshelloApi(){
        TestRestTemplate rest = new TestRestTemplate();
        ResponseEntity<String> res= rest.getForEntity("http://localhost:8080/hello?name=",String.class); //메시지 바디를 String 클래스로 바인딩
        // 응답 검증
        // status 200
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
