package com.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class AdminFlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminFlightApplication.class, args);
	}
	
	

	 @Bean
	    public Docket api() {
	       return new Docket(DocumentationType.SWAGGER_2);
	    }
}