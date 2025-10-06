package com.fmgarcia.service.management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fmgarcia.service.management.files.service.StorageService;

import jakarta.annotation.Resource;

@SpringBootApplication
public class FmgarciaServiceManagementApplication implements CommandLineRunner {
	
	@Resource
	StorageService storageService;

	public static void main(String[] args) {
		SpringApplication.run(FmgarciaServiceManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		storageService.init();
		
	}

}
