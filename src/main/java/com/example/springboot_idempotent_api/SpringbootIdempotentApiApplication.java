package com.example.springboot_idempotent_api;

import com.example.springboot_idempotent_api.model.Customer;
import com.example.springboot_idempotent_api.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootIdempotentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootIdempotentApiApplication.class, args);
	}

    @Bean
    CommandLineRunner init(CustomerRepository repository) {
        return args -> {
            repository.save(new Customer("Nafiul Islam", "nafiul@example.com"));
        };
    }
}
