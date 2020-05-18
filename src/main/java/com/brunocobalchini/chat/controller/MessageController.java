package com.brunocobalchini.chat.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brunocobalchini.chat.model.Message;
import com.brunocobalchini.chat.repository.MessageRepository;

@RestController
@RequestMapping(path = "/messages")
public class MessageController {

	@Autowired
	private MessageRepository messageRepo;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Message> getMessageById(@PathVariable UUID id) {
		Optional<Message> msg  = messageRepo.findById(id);
		if (msg.isPresent()) {
			return ResponseEntity.ok(msg.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
		}
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteMessageById(@PathVariable UUID id) {
		messageRepo.deleteById(id);
	}

	@PostMapping
	public ResponseEntity<Message> postMessage(@RequestBody Message message) {
		message.setId(null);
		message = messageRepo.save(message);
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Message> putMessage(@PathVariable UUID id, @RequestBody Message message) {
		Optional<Message> oldMessage = messageRepo.findById(id);
		if (oldMessage.isPresent()) {
			if (message.getReceiverId() != null) {
				oldMessage.get().setReceiverId(message.getReceiverId());
			}
			if (message.getSenderId() != null) {
				oldMessage.get().setSenderId(message.getSenderId());
			}
			if (!StringUtils.isEmpty(message.getContent())) {
				oldMessage.get().setContent(message.getContent());
			}				
			return ResponseEntity.status(HttpStatus.OK).body(messageRepo.save(oldMessage.get()));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
		}
	}
}
