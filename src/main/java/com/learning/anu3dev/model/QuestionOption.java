package com.learning.anu3dev.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Data
@Table(name = "question_options")
public class QuestionOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "question_id", nullable = false)
	private PollQuestion questionId;
	
	@Column(name = "option_text")
	private String optionText;
	
	@Version
	private Long version;
	
	@Column(nullable = false)
	private int count = 0;
}
