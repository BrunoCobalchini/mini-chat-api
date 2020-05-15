package com.brunocobalchini.chat.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "message")
@JsonIgnoreProperties(ignoreUnknown = true)
@EntityListeners(AuditingEntityListener.class)
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name =  "sender_id", nullable = false)
	private String senderId;

	@Column(name =  "receiver_id", nullable = false)
	private UUID receiverId;

	@Column(nullable = false)
	private String content;

	@CreatedDate
	@Column(name =  "created_on", nullable = false)
	private LocalDateTime createdOn;

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public UUID getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(UUID receiverId) {
		this.receiverId = receiverId;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
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