package com.killian.SpringBoot.ExamApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication (exclude =  SecurityAutoConfiguration.class)
@ComponentScan("com.killian.SpringBoot")
@EntityScan(basePackages = "com.killian.SpringBoot.ExamApp.models")
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}	
}
