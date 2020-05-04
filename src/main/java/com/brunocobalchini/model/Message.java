package com.brunocobalchini.model;

import java.time.LocalTime;

public class Message {

	private String content;

	private LocalTime sendAt;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalTime getSendAt() {
		return sendAt;
	}

	public void setSendAt(LocalTime sendAt) {
		this.sendAt = sendAt;
	}
}