package com.devjoliveira.mastergymsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MastergymsysApplication {

	public static void main(String[] args) {
		SpringApplication.run(MastergymsysApplication.class, args);
	}

}
