package com.miskolc.sound_processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SoundProcessorApplication {

	@Bean
	public ReceiveSoundLevel receiveSoundLevel() {
		return new ReceiveSoundLevel();
	}
	public static void main(String[] args) {
		SpringApplication.run(SoundProcessorApplication.class, args);
	}

}
