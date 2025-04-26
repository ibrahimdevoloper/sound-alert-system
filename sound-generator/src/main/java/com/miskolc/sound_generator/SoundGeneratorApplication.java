package com.miskolc.sound_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.Random;

@SpringBootApplication
@EnableScheduling
public class SoundGeneratorApplication {
	private final Random random = new Random();
	private final SendToQueue sendToQueue = new SendToQueue();
	public static void main(String[] args) {
		SpringApplication.run(SoundGeneratorApplication.class, args);
	}

	@Scheduled(fixedRate = 2000)
	public void sendRandomSoundLevel() {
		int decibel = 30 + random.nextInt(91); // 30 to 120 inclusive
		sendToQueue.sendMessage(String.valueOf(decibel));
		System.out.println("Sent decibel reading: " + decibel + " dB");
	}

}
