package com.aws.albums;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlbumsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumsApplication.class, args);
	}

}

//TODO:
//focus on hibernate, annotations, JPA
//read up on difference between hibernate and JPA
//read up on spring containers and how app.prop is loaded into app
//read meaning of @Configuration, @Bean, @Controller, @Service, @Repository, @Component, @Singleton, @ComponentScan, @Autowired,