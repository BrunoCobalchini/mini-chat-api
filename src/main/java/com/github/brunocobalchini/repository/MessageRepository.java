package com.github.brunocobalchini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunocobalchini.chat.model.Message;

public interface MessageRepository extends JpaRepository<Message, String>{
	
}