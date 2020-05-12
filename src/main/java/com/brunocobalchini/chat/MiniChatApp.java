package com.brunocobalchini.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MiniChatApp {

	public static void main(String[] args) {
		SpringApplication.run(MiniChatApp.class, args);
	}

}