package com.github.brunocobalchini.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@GetMapping
	public Collection<Message> getMessages(){
		return messageRepo.findAll();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Message> getMessageById(@PathVariable Integer id) {
		if (messageRepo.existsById(id)) {
			return ResponseEntity.ok(messageRepo.findById(id).get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
		}
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteMessageById(@PathVariable Integer id) {
		messageRepo.deleteById(id);
	}

	@PostMapping(path = "/{messages}")
	public ResponseEntity<Message> postMessage(@PathVariable Message messages, @RequestBody Message message) {

		message = messageRepo.save(message);
		return ResponseEntity.status(HttpStatus.CREATED).body(message);
	}
		
	@PutMapping(path = "/{id}")
	public ResponseEntity<Message> putMessage(@PathVariable Integer id, @RequestBody Message message) {
		
		Message oldMessage = messageRepo.findById(id).get();
		oldMessage = messageRepo.save(oldMessage);
		return ResponseEntity.status(HttpStatus.OK).body(oldMessage);
	}
}
