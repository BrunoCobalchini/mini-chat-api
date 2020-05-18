package com.brunocobalchini.chat.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brunocobalchini.chat.model.Conversation;
import com.brunocobalchini.chat.repository.ConversationRepository;

@RestController
@RequestMapping(path = "/conversations")
public class ConversationController {

	@Autowired
	private ConversationRepository conversationRepo;

	@GetMapping
	public ResponseEntity<Collection<Conversation>> getConversations(Principal principal) {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
		if (token != null && !StringUtils.isEmpty(token.getName())) {
			return ResponseEntity.ok(conversationRepo.findByMembers(token.getName()));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();	
		}
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Conversation> getConversationById(@PathVariable UUID id) {
		Optional<Conversation> conv = conversationRepo.findById(id);
		if (conv.isPresent()) {
			return ResponseEntity.ok(conv.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
		}
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteConversationById(@PathVariable UUID id) {
		conversationRepo.deleteById(id);
	}

	@PostMapping
	public ResponseEntity<Conversation> postConversation(@RequestBody Conversation conversation) {
		conversation.setId(null);
		conversation = conversationRepo.save(conversation);
		return ResponseEntity.status(HttpStatus.CREATED).body(conversation);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Conversation> putConversation(@PathVariable UUID id, @RequestBody Conversation conversation) {
		if (conversationRepo.existsById(id)) {
			Conversation oldConversation = conversationRepo.findById(id).get();
			if (conversation.getMembers() != null && !conversation.getMembers().isEmpty()) {
				oldConversation.setMembers(conversation.getMembers());
			}
			if (conversation.getMessages() != null && !conversation.getMessages().isEmpty()) {
				oldConversation.setMessages(conversation.getMessages());
			}
			oldConversation = conversationRepo.save(oldConversation);
			return ResponseEntity.status(HttpStatus.OK).body(oldConversation);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
		}
	}
}