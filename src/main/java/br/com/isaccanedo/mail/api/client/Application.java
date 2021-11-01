package br.com.isaccanedo.mail.api.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages= { 
		"br.com.isaccanedo.mail.api.client", 
		"br.com.isaccanedo.mail.api.client.controller.rest",
		"br.com.isaccanedo.mail.api.client.dao",
		"br.com.isaccanedo.mail.api.client.dao.rest",
		"br.com.isaccanedo.mail.api.client.domain.dto",
		"br.com.isaccanedo.mail.api.client.entity",
		"br.com.isaccanedo.mail.api.client.exceptions",
		"br.com.isaccanedo.mail.api.client.repository",
		"br.com.isaccanedo.mail.api.client.service"
})
@EntityScan(basePackages = "br.com.isaccanedo.mail.api.client.entity")
@EnableJpaRepositories(basePackages = "br.com.isaccanedo.mail.api.client.repository")
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
