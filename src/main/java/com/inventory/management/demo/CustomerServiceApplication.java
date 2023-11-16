package com.inventory.management.demo;

import com.inventory.management.demo.entity.Customer;
import com.inventory.management.demo.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

@SpringBootApplication
@Slf4j
public class CustomerServiceApplication {
	@Autowired
	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}


	@Bean
	public CommandLineRunner run() throws Exception {

		return args -> {
			repository.deleteAll();
			Customer a1 = new Customer(1000l,"John");
			Customer a2 = new Customer(2000l,"Arun");
			Customer a3 = new Customer(3000l,"Somya");
			Customer a4 = new Customer(4000l,"Samantha");
			List<Customer> customerList =Arrays.asList(a1,a2,a3,a4);
			repository.saveAll(customerList);
			log.info("Data initilization competed for customer service");
		};
	}
}
