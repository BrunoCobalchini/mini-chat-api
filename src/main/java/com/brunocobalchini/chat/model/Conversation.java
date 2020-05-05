package com.brunocobalchini.chat.model;

import java.util.List;

public class Conversation {

	private String id;  // invitar as pessoas para a conversa com id
	
	private List<Integer> members;
	
	private List<Integer> messages;
	
	public List<Integer> getMessages() {
		return messages;
	}

	public void setMessages(List<Integer> messages) {
		this.messages = messages;
	}

	public List<Integer> getMembers() {
		return members;
	}

	public void setMembers(List<Integer> members) {
		this.members = members;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}