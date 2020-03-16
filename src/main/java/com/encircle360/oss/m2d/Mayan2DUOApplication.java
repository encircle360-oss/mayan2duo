package com.encircle360.oss.m2d;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableFeignClients
@SpringBootApplication
public class Mayan2DUOApplication {

	public static void main(String[] args) {
		SpringApplication.run(Mayan2DUOApplication.class, args);
	}
}
