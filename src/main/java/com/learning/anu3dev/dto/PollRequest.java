package com.learning.anu3dev.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PollRequest {
	@JsonProperty("isActive")
	private boolean isActive;
	private String pollTitle;
	private Long pollId;
	private List<QuestionRequest> questions;
}
