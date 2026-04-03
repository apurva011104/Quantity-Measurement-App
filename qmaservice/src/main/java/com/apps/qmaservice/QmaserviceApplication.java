package com.apps.qmaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QmaserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QmaserviceApplication.class, args);
		System.out.println("QMA Service is running...");
	}

}
