package com.github.brunocobalchini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunocobalchini.chat.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	
}
