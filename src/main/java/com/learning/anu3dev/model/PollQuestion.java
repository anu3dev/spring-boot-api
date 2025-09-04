package com.learning.anu3dev.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "poll_questions")
public class PollQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name = "poll_id", nullable = false)
	private Poll poll;
	
	@Column(name = "is_multi_select")
	private boolean isMultiSelect;
	
	private String questionTitle;
	
	@OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL,  orphanRemoval = true)
	private List<QuestionOption> options = new ArrayList<>();
}
