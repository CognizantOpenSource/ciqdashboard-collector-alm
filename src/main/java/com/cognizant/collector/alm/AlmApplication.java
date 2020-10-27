package com.cognizant.collector.alm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableFeignClients
@SpringBootApplication
@EnableScheduling
@EnableAutoConfiguration
@EnableEncryptableProperties
public class AlmApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlmApplication.class, args);
	}

}
