package com.brunocobalchini.chat.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping
	public Collection<User> getUsers(){
		return userRepo.findAll();
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Integer id) {
		Optional<User> usu = userRepo.findById(id);
		if (usu.isPresent()) {
			return ResponseEntity.ok(usu.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
		}
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteUserById(@PathVariable Integer id) {
		userRepo.deleteById(id);
	}

	@PostMapping
	public ResponseEntity<User> postUser(@RequestBody User user) {
		user.setId(null);
		if (StringUtils.isEmpty(user.getEmail())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if (StringUtils.isEmpty(user.getFullName())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		user = userRepo.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<User> putUser(@PathVariable Integer id, @RequestBody User user) {
		Optional<User> usu = userRepo.findById(id);
		if (usu.isPresent()) {
			User oldUser = usu.get();
			if (!StringUtils.isEmpty(user.getEmail())) {
				oldUser.setEmail(user.getEmail());
			}
			if (!StringUtils.isEmpty(user.getFullName())) {
				oldUser.setFullName(user.getFullName());
			}
			if (!StringUtils.isEmpty(user.getPassword())) {
				oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
			}				
			oldUser = userRepo.save(oldUser);
			return ResponseEntity.status(HttpStatus.OK).body(oldUser);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
		}
	}
}