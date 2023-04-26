package tobyspring.config.autoconfig;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.boot.jdbc.metadata.HikariDataSourcePoolMetadata;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tobyspring.config.ConditionalMyOnClass;
import tobyspring.config.EnableMyConfigurationProperties;
import tobyspring.config.MyAutoConfiguration;

import javax.sql.DataSource;
import java.sql.Driver;

@MyAutoConfiguration
@ConditionalMyOnClass("org.springframework.jdbc.core.JdbcOperations") // jdbc의존성 패키지가 존재할 때 자동구성
@EnableMyConfigurationProperties(MyDataSourceProperties.class) // DataSourceConfig가 Conditional 조건이 구성되면 속성을 주입할 클래스를 bean 으로 생성
@EnableTransactionManagement
public class DataSourceConfig {
    //bean이 순서대로 생성된다고 가정할 때
    @Bean
    @ConditionalMyOnClass("com.zaxxer.hikari.HikariDataSource")
    @ConditionalOnMissingBean
    DataSource hikariDataSource(MyDataSourceProperties properties){
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setDriverClassName(properties.getDriverClassName()); // DB 종류에 따른 Driver
        dataSource.setJdbcUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPasswd());

        return dataSource;
    }
    @Bean
    @ConditionalOnMissingBean /// 앞에 메소드에서 DataSource 빈을 만들었으면 DataSource빈이 존재하고 hikari를 사용. 만약 hikari가 없으면 simpleDriverDataSource를 사용.
    DataSource dataSource(MyDataSourceProperties properties) throws ClassNotFoundException {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass((Class<? extends Driver>) Class.forName(properties.getDriverClassName()));
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPasswd());
        return dataSource;

    }
    @Bean
    @ConditionalOnSingleCandidate(DataSource.class) // DataSource 타입의 빈 메소드가 딱 한개만 등록되어 있다면 그 DataSource를 가져와서 사용하겠다는 의미
    @ConditionalOnMissingBean
    JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    @Bean
    @ConditionalOnSingleCandidate(DataSource.class) // DataSource 타입의 빈 메소드가 실행될때 해당 데이터 소스 타입이 딱 한개만 등록되어 있다면 하나의 DataSource를 가져와서 사용하겠다는 의미
    @ConditionalOnMissingBean
    JdbcTransactionManager jdbcTransactionManager(DataSource dataSource){
        return new JdbcTransactionManager(dataSource);
    }
}
