package tobyspring.helloboot;

// 컨테이너를 띄우고 그 빈을 가져오는 방식으로 테스트


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@HellobootTest
public class DataSourceTest {
    @Autowired  // 해당 타입에 bean이 있으면 그것을 가져올 때 쓰는 애노테이션
    DataSource dataSource;

    @Test
    void connect() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.close();
    }
}
