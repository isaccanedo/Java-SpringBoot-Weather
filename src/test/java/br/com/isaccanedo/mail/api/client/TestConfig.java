package br.com.isaccanedo.mail.api.client;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
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
public class TestConfig {}
