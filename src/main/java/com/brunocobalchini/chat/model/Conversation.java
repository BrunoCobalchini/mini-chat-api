package com.brunocobalchini.chat.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "Conversation")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Conversation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private String id; 
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Conversation")
	@JsonManagedReference("Conversation")
	private List<Integer> members;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "Conversation")
	@JsonManagedReference("Conversation")
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