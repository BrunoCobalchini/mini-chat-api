package com.brunocobalchini.chat.repository;

import java.util.Collection;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunocobalchini.chat.model.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

	Set<Message> findByIdIn(Collection<Integer> ids); 
	
}