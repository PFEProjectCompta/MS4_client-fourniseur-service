package com.ges.clientfourniseurservice;

import com.ges.clientfourniseurservice.config.InitialData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class ClientFourniseurServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientFourniseurServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(){
		return args -> {
			InitialData.ajouterClient();
			InitialData.ajouterFournisseur();
		};
	};
}
