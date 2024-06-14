package com.gml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
public class ClientApplicationHexa {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplicationHexa.class, args);
	}
/*
	@Bean
	CommandLineRunner init( ClientJpaRepository clientJpaRepository) {
		return args -> {
			Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
				Client client = new Client(name, name.toLowerCase() + "@domain.com",
						""+name.length(),new Date(),new Date(),new Date(),name,new Date());
				clientJpaRepository.save(client);
			});
			//clientJpaRepository.findAll().forEach(System.out::println);
		};
	}*/
}
