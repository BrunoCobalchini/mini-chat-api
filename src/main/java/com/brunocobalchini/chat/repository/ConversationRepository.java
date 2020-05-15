package com.brunocobalchini.chat.repository;

import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunocobalchini.chat.model.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, UUID> {
	
	Set<Conversation> findByMembers(String member);

}