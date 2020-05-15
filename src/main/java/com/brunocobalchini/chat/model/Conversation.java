package com.brunocobalchini.chat.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "conversation")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Conversation {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	private UUID id;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "conversation_member", joinColumns = @JoinColumn(name = "conversation_id"))
	@Column(name = "member_id", nullable = false)
	private Set<String> members = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "conversation_message", joinColumns = @JoinColumn(name = "conversation_id"))
	@Column(name = "message_id", nullable = false)
	private Set<Integer> messages = new HashSet<>();

	public Set<Integer> getMessages() {
		return messages;
	}

	public void setMessages(Set<Integer> messages) {
		this.messages = messages;
	}

	public Set<String> getMembers() {
		return members;
	}

	public void setMembers(Set<String> members) {
		this.members = members;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

}