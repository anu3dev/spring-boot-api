package com.learning.anu3dev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.anu3dev.model.Poll;

public interface PollRepo extends JpaRepository<Poll, Long> {
	List<Poll> findByIsActiveTrueOrderByCreatedAtDesc();
}
