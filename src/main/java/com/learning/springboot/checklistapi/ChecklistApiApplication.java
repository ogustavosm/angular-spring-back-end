package com.learning.springboot.checklistapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ChecklistApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChecklistApiApplication.class, args);
	}

	@Profile("local")
	@Bean
	public WebMvcConfigurer corsLocalConfig(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200")
						.allowedMethods("GET", "PUT", "OPTIONS", "POST", "DELETE", "PATCH")
						.maxAge(900)
						.allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization");
			}
		};
	}

	
	@Profile("aws")
	@Bean
	public WebMvcConfigurer corsAwsConfig(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://checklist-spa.s3-website-sa-east-1.amazonaws.com")
						.allowedMethods("GET", "PUT", "OPTIONS", "POST", "DELETE", "PATCH")
						.maxAge(900)
						.allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization");
			}
		};
	}

	

}
