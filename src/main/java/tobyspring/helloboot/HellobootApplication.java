package tobyspring.helloboot;


import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import tobyspring.config.MySpringBootApplication;

import javax.annotation.PostConstruct;


@MySpringBootApplication
public class HellobootApplication {
	private final JdbcTemplate jdbcTemplate;

	public HellobootApplication(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@PostConstruct
	void init(){
		jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
	}

	public static void main(String[] args){
//		run(HellobootApplication.class, args);
		SpringApplication.run(HellobootApplication.class,args);
	}

}