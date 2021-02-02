package com.itheima.oauth.oauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
public class OauthApplication {


	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}

}
