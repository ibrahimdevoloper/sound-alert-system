package com.miskolc.alert_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlertClientApplication {

	@Bean
	public ReceiveSoundAlert receiveSoundAlert() {
		return new ReceiveSoundAlert();
	}

	public static void main(String[] args) {
		SpringApplication.run(AlertClientApplication.class, args);
	}

}
