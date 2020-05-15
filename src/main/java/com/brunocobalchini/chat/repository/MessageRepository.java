package com.brunocobalchini.chat.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunocobalchini.chat.model.Message;

public interface MessageRepository extends JpaRepository<Message, UUID> {

}