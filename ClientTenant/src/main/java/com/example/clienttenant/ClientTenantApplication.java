package com.example.clienttenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.example.clienttenant.tenant.repository.mongo")

@EnableJpaRepositories(basePackages = "com.example.clienttenant.tenant.repository.jpa")
public class ClientTenantApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientTenantApplication.class, args);
	}

}
