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
		
		// First, create 6 users
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
		
		User thor = new User();
		thor.setFullName("Thor");
		thor.setEmail("thor@thor.com");
		thor.setPassword(passwordEncoder.encode("unknown"));
		thor = userRepo.save(thor);
		
		User hela = new User();
		hela.setFullName("Hela");
		hela.setEmail("hela@hela.com");
		hela.setPassword(passwordEncoder.encode("unknown"));
		hela = userRepo.save(hela);
		
		User thanos = new User();
		thanos.setFullName("Thanos");
		thanos.setEmail("thanos@thanos.com");
		thanos.setPassword(passwordEncoder.encode("unknown"));
		thanos = userRepo.save(thanos);
		
		// This is the "good guys" group
		Conversation convGoodGuys = new Conversation();
		convGoodGuys.getMembers().add(fury.getEmail());
		convGoodGuys.getMembers().add(tony.getEmail());		
		convGoodGuys.getMembers().add(steve.getEmail());
		convGoodGuys.getMembers().add(thor.getEmail());
		convGoodGuys = conversationRepo.save(convGoodGuys);

		Message message = new Message();
		message.setContent("Avengers, assemble!");
		message.setSenderId(fury.getEmail());
		message.setReceiverId(convGoodGuys.getId());
		message = messageRepo.save(message);
		convGoodGuys.getMessages().add(message.getId());
		
		message = new Message();
		message.setContent("I am Iron Man");
		message.setSenderId(tony.getEmail());
		message.setReceiverId(convGoodGuys.getId());
		message = messageRepo.save(message);
		convGoodGuys.getMessages().add(message.getId());
		
		message = new Message();
		message.setContent("I Can Do This All Day");
		message.setSenderId(steve.getEmail());
		message.setReceiverId(convGoodGuys.getId());
		message = messageRepo.save(message);
		convGoodGuys.getMessages().add(message.getId());

		message = new Message();
		message.setContent("I am Thor, son of Odin!");
		message.setSenderId(thor.getEmail());
		message.setReceiverId(convGoodGuys.getId());
		message = messageRepo.save(message);
		convGoodGuys.getMessages().add(message.getId());		
		
		conversationRepo.save(convGoodGuys);
		
		// This is the "bad guys" group
		Conversation convBadGuys = new Conversation();
		convBadGuys.getMembers().add(hela.getEmail());
		convBadGuys.getMembers().add(thanos.getEmail());		
		convBadGuys = conversationRepo.save(convBadGuys);
		
		message = new Message();
		message.setContent("I AM...INEVITABLE.");
		message.setSenderId(thanos.getEmail());
		message.setReceiverId(convBadGuys.getId());
		message = messageRepo.save(message);
		convGoodGuys.getMessages().add(message.getId());
		
		message = new Message();
		message.setContent("I'm not a queen or a monster. I'm the Goddess of Death.");
		message.setSenderId(hela.getEmail());
		message.setReceiverId(convBadGuys.getId());
		message = messageRepo.save(message);
		convGoodGuys.getMessages().add(message.getId());
		
		conversationRepo.save(convBadGuys);
		
		// This is the "family" group (Thor and Hela)
		Conversation convFamily = new Conversation();
		convFamily.getMembers().add(hela.getEmail());
		convFamily.getMembers().add(thor.getEmail());		
		convFamily = conversationRepo.save(convFamily);
		
		message = new Message();
		message.setContent("I am the god of Thunder!");
		message.setSenderId(thor.getEmail());
		message.setReceiverId(convFamily.getId());
		message = messageRepo.save(message);
		convFamily.getMessages().add(message.getId());
		
		message = new Message();
		message.setContent("I will kill you, brother.");
		message.setSenderId(hela.getEmail());
		message.setReceiverId(convFamily.getId());
		message = messageRepo.save(message);
		convFamily.getMessages().add(message.getId());
		
		conversationRepo.save(convFamily);
	}

}