package com.brunocobalchini.chat.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Message")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Integer id;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name =  "sender_id", nullable = false)
	private Integer senderId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name =  "receiver_id", nullable = false)
	private Integer receiverId;

	@Column(nullable = false)
	private String content;

	@Column(name =  "created_on", nullable = false)
	private LocalDateTime createdOn;
	

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}