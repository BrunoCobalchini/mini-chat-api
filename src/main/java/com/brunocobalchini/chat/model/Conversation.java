package com.brunocobalchini.chat.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "conversation")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Conversation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 

	@ElementCollection
	@CollectionTable(name = "conversation_member", joinColumns = @JoinColumn(name = "conversation_id"))
	@Column(name = "member_id", nullable = false)
	private Set<Integer> members;

	@ElementCollection
	@CollectionTable(name = "conversation_message", joinColumns = @JoinColumn(name = "conversation_id"))
	@Column(name = "message_id", nullable = false)
	private Set<Integer> messages;

	public Set<Integer> getMessages() {
		return messages;
	}

	public void setMessages(Set<Integer> messages) {
		this.messages = messages;
	}

	public Set<Integer> getMembers() {
		return members;
	}

	public void setMembers(Set<Integer> members) {
		this.members = members;
	}

	public Integer getId() {
		return id;
	}

}