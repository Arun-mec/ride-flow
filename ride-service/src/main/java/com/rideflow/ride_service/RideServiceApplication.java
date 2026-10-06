package com.rideflow.ride_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rideflow.ride_service", "com.rideflow.common_security"})
public class RideServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideServiceApplication.class, args);
	}

}
