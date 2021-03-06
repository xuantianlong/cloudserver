package com.itheima.client.web2.clientweb2;

import com.feginapi.config.EnableRyFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication
@EnableDiscoveryClient
@EnableRyFeignClients
public class Clientweb2Application {

	public static void main(String[] args) {
		SpringApplication.run(Clientweb2Application.class, args);
	}

}
