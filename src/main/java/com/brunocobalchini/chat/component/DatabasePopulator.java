package com.brunocobalchini.chat.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.brunocobalchini.chat.model.Conversation;
import com.brunocobalchini.chat.model.Message;
import com.brunocobalchini.chat.model.User;
import com.brunocobalchini.chat.repository.ConversationRepository;
import com.brunocobalchini.chat.repository.MessageRepository;
import com.brunocobalchini.chat.repository.UserRepository;

@Component
public class DatabasePopulator {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private MessageRepository messageRepo;

	@Autowired
	private ConversationRepository conversationRepo;
	
	@PostConstruct
	public void populate() {
		
		User fury = new User();
		fury.setFullName("Nick Fury");
		fury.setEmail("nick@nick.com");
		fury.setPassword("unknown");
		fury = userRepo.save(fury);
		
		User tony = new User();
		tony.setFullName("Tony Stark");
		tony.setEmail("tony@tony.com");
		tony.setPassword("unknown");
		tony = userRepo.save(tony);
		
		User steve = new User();
		steve.setFullName("Steve Rogers");
		steve.setEmail("steve@steve.com");
		steve.setPassword("unknown");
		steve = userRepo.save(steve);
		
		// TODO: create user "Nick Fury" and save to the database
		// TODO: create user "Tony Stark" and save to the database
		// TODO: create user "Steve Rogers" and save to the database
		
		Conversation conv = new Conversation();
		conv.getMembers().add(fury.getId());
		conv.getMembers().add(tony.getId());		
		conv.getMembers().add(steve.getId());
		conv.getMessages().add(fury.getId());
		conv.getMessages().add(tony.getId());
		conv.getMessages().add(steve.getId());
		
		// TODO: Create conversation with those 3 users and save to the database

		// TODO: Create Message from user "Nick Fury" with content: "Avengers, assemble!" and save to the database
		// TODO: Create Message from user "Tony Stark" with content: "I am Iron Man." and save to the database
		// TODO: Create Message from user "Tony Stark" with content: "I Can Do This All Day." and save to the database
		
		Message message = new Message();
		message.setContent("Avengers, assemble!");
		message.setSenderId(fury.getId());
		message.setReceiverId((tony.getId())); // TODO:  conversation id
		messageRepo.save(message);
		
		message = new Message();
		message.setContent("I am Iron Man");
		message.setSenderId(tony.getId());
		message.setReceiverId(steve.getId()); // TODO:  conversation id
		messageRepo.save(message);
		
		message = new Message();
		message.setContent("I Can Do This All Day");
		message.setSenderId(steve.getId());
		message.setReceiverId((fury.getId())); // TODO:  conversation id
		messageRepo.save(message);
		
	}

}