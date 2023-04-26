package tobyspring.helloboot;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository // component 애노테이션을 메타 애노테이션으로 가지는 Repository 애노테이션
public class HelloRepositoryJdbc implements HelloRepository{
    private final JdbcTemplate jdbcTemplate;

    public HelloRepositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Hello findHello(String name) {

        try {
            // jdbc에서 컬럼을 가져오는 메서드가 quertForObject이고 여러 컬럼을 가져올 때 RowMapper를 사용해서 한번에 매핑할 할 수 있다.
            return jdbcTemplate.queryForObject("select * from hello where name ='" + name + "'",
                    (rs, rowNum) -> new Hello(
                            rs.getString("name"),rs.getInt("count")
                    ));
        }
        catch (EmptyResultDataAccessException e) { // 없으면 에러처리하는
            return null;
        }
    }

    @Override
    public void increaseCount(String name) {
        Hello hello = findHello(name);
        if (hello == null ) jdbcTemplate.update("insert into hello values(?,?)",name,1);
        else jdbcTemplate.update("update hello set count = ? where name = ?", hello.getCount() +1, name);
    }
}
