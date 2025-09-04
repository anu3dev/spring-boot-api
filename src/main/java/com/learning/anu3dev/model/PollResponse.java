package com.learning.anu3dev.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "poll_responses")
@Data
public class PollResponse {
	@EmbeddedId
	private PollResponseId id;
	
	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@MapsId("questionId")
	@JoinColumn(name = "question_id", nullable = false)
	private PollQuestion question;
	
	@ManyToOne
	@MapsId("optionId")
	@JoinColumn(name = "option_id", nullable = false)
	private QuestionOption option;
	
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
}
