package com.github.brunocobalchini.controller;

import java.util.Collection;

import javax.annotation.PostConstruct;

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

import com.brunocobalchini.chat.model.User;
import com.brunocobalchini.chat.repository.UserRepository;

@RestController
@RequestMapping(path = "/users")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@GetMapping
	public Collection<User> getUsers(){
		return userRepo.findAll();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		if (userRepo.existsById(id)) {
			return ResponseEntity.ok(userRepo.findById(id).get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
		}
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteUserById(@PathVariable Integer id) {
		userRepo.deleteById(id);
	}

	@PostMapping(path = "/{users}")
	public ResponseEntity<User> postUser(@PathVariable User users, @RequestBody User user) {

		user = userRepo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
		
	@PutMapping(path = "/{id}")
	public ResponseEntity<User> putUser(@PathVariable Integer id, @RequestBody User user) {
		
		User oldUser = userRepo.findById(id).get();
		oldUser = userRepo.save(oldUser);
		return ResponseEntity.status(HttpStatus.OK).body(oldUser);
	}
}