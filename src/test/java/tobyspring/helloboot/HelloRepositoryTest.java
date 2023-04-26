package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HellobootTest
public class HelloRepositoryTest {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    HelloRepository helloRepository;


    @Test // 쿼리 실패(DB에 조건에 맞는 name이 없을 때 반환하는 객체를 찾고싶어서 사용)
    void findHelloFailed(){
        Assertions.assertThat(helloRepository.findHello("Toby")).isNull();
    }
    @Test
    void increaseCount(){
        //첫 DB 쿼리시 "Toby" 가 없기 때문에 0을 반환
        Assertions.assertThat(helloRepository.countOf("Toby")).isEqualTo(0);


        helloRepository.increaseCount("Toby");
        Assertions.assertThat(helloRepository.countOf("Toby")).isEqualTo(1);

        helloRepository.increaseCount("Toby");
        Assertions.assertThat(helloRepository.countOf("Toby")).isEqualTo(2);
    }
}
