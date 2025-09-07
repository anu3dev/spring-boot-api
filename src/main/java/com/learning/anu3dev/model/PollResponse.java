package com.learning.anu3dev.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "poll_responses")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)   // use only ID
@ToString(onlyExplicitlyIncluded = true)            // don’t include relations
public class PollResponse {

    @EmbeddedId
    @EqualsAndHashCode.Include
    @ToString.Include
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
    @ToString.Include
    private LocalDateTime createdAt = LocalDateTime.now();
}