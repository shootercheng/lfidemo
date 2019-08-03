package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
@ServletComponentScan(basePackages = {"com.example.listener", "com.example.filter"})
public class LfiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LfiApplication.class, args);
	}

}
