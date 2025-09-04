package com.learning.anu3dev.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class QuestionRequest {
	private String questionTitle;
	@JsonProperty("isActive")
    private boolean isActive;
	@JsonProperty("isMultiSelect")
    private boolean isMultiSelect;
    private List<String> options;
}
