package com.esprit.achat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AchatApplication {

	public static void main(String[] args) {

		SpringApplication.run(AchatApplication.class, args);
	}

}
