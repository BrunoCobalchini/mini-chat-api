package com.brunocobalchini.chat.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brunocobalchini.chat.model.Conversation;
import com.brunocobalchini.chat.model.Message;
import com.brunocobalchini.chat.model.User;
import com.github.brunocobalchini.repository.ConversationRepository;
import com.github.brunocobalchini.repository.MessageRepository;
import com.github.brunocobalchini.repository.UserRepository;

@Component
public class DatabasePopulator {

	@Autowired
	private UserRepository userRepo;
	private MessageRepository messageRepo;
	private ConversationRepository conversationRepo;
	
	@PostConstruct
	public void populate() {
		
		User user = new User();
		user.setFullName("Nick Fury");
		user.setEmail("nick@nick.com");
		user.setPassword("unknown");
		userRepo.save(user);
		
		user = new User();
		user.setFullName("Tony Stark");
		user.setEmail("tony@tony.com");
		user.setPassword("unknown");
		userRepo.save(user);
		
		user = new User();
		user.setFullName("Steve Rogers");
		user.setEmail("steve@steve.com");
		user.setPassword("unknown");
		userRepo.save(user);
		
		// TODO: create user "Nick Fury" and save to the database
		// TODO: create user "Tony Stark" and save to the database
		// TODO: create user "Steve Rogers" and save to the database
		
		Conversation conv = new Conversation();
		
		

		// TODO: Create conversation with those 3 users and save to the database

		// TODO: Create Message from user "Nick Fury" with content: "Avengers, assemble!" and save to the database
		// TODO: Create Message from user "Tony Stark" with content: "I am Iron Man." and save to the database
		// TODO: Create Message from user "Tony Stark" with content: "I Can Do This All Day." and save to the database
		Message message = new Message();
		message.setContent("Avengers, assemble!");
		message.setSenderId(1);
		message.setReceiverId(1);
		messageRepo.save(message);
		
		message = new Message();
		message.setContent("I am Iron Man");
		message.setSenderId(2);
		message.setReceiverId(2);
		messageRepo.save(message);
		
		message = new Message();
		message.setContent("I Can Do This All Day");
		message.setSenderId(3);
		message.setReceiverId(3);
		messageRepo.save(message);
		
		
	}

}
