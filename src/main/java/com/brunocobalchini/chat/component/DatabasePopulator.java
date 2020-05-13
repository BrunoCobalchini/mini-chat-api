package com.brunocobalchini.chat.component;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void populate() {
		
		User fury = new User();
		fury.setFullName("Nick Fury");
		fury.setEmail("nick@nick.com");
		fury.setPassword(passwordEncoder.encode("unknown"));
		fury = userRepo.save(fury);
		
		User tony = new User();
		tony.setFullName("Tony Stark");
		tony.setEmail("tony@tony.com");
		tony.setPassword(passwordEncoder.encode("unknown"));
		tony = userRepo.save(tony);
		
		User steve = new User();
		steve.setFullName("Steve Rogers");
		steve.setEmail("steve@steve.com");
		steve.setPassword(passwordEncoder.encode("unknown"));
		steve = userRepo.save(steve);
		
		Conversation conv = new Conversation();
		conv.getMembers().add(fury.getId());
		conv.getMembers().add(tony.getId());		
		conv.getMembers().add(steve.getId());
		conv = conversationRepo.save(conv);

		Message message = new Message();
		message.setContent("Avengers, assemble!");
		message.setSenderId(fury.getId());
		message.setReceiverId(conv.getId());
		message = messageRepo.save(message);
		conv.getMessages().add(message.getId());
		
		message = new Message();
		message.setContent("I am Iron Man");
		message.setSenderId(tony.getId());
		message.setReceiverId(conv.getId());
		message = messageRepo.save(message);
		conv.getMessages().add(message.getId());
		
		message = new Message();
		message.setContent("I Can Do This All Day");
		message.setSenderId(steve.getId());
		message.setReceiverId(conv.getId());
		message = messageRepo.save(message);
		conv.getMessages().add(message.getId());
		
		conversationRepo.save(conv);
	}

}