package com.itheima.client.web.clientweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientwebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientwebApplication.class, args);
	}

}
