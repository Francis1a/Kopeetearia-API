package com.accenture.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(WsApplication.class, args);

	}

}
