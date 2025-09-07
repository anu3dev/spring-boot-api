package com.learning.anu3dev.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "poll_questions")
@Getter @Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class PollQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(name = "created_at")
    @ToString.Include
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "is_active")
    @ToString.Include
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "poll_id", nullable = false)
    @JsonBackReference("poll-questions")           // replaces @JsonIgnore
    private Poll poll;

    @Column(name = "is_multi_select")
    @ToString.Include
    private boolean isMultiSelect;

    @ToString.Include
    private String questionTitle;

    @OneToMany(mappedBy = "questionId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference("question-options")
    private List<QuestionOption> options = new ArrayList<>();
}