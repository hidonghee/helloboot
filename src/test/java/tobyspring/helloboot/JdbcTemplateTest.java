package tobyspring.helloboot;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@HellobootTest
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach // 애플리케이션이 시작할 때 초기화하는 작업. 테스트를 할 때 다른 테스트에 영향을 받지 않기 위해
    void init() {
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    } // 몇번 hello라는 인사를 했는가를 저장하기 위한 테스트 테이블
    @Test
    void insertAndQuery(){
        jdbcTemplate.update("insert into hello values (?,?)","Toby",3);
        jdbcTemplate.update("insert into hello values (?,?)","Spring",1);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);

        Assertions.assertThat(count).isEqualTo(2);

    }

}
