package com.brunocobalchini.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brunocobalchini.chat.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(String email);
}