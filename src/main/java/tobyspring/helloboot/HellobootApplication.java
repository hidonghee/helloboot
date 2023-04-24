package tobyspring.helloboot;


import tobyspring.config.MySpringBootApplication;

import static tobyspring.helloboot.mySpringApplication.run;

@MySpringBootApplication
public class HellobootApplication {
	public static void main(String[] args){
		run(HellobootApplication.class, args);
	}

}