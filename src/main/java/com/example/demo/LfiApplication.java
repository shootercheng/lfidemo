package com.example.demo;

import com.example.config.FilterConfig;
import com.example.config.ServletConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
@ServletComponentScan(basePackages = {"com.example.listener", "com.example.filter"})
@Import(value = {FilterConfig.class, ServletConfig.class})
public class LfiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LfiApplication.class, args);
	}

}
