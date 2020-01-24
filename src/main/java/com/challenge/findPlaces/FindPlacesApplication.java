package com.challenge.findPlaces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FindPlacesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FindPlacesApplication.class, args);
	}

}
