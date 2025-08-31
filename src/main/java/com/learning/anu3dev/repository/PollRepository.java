package com.learning.anu3dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.anu3dev.model.Poll;

public interface PollRepository extends JpaRepository<Poll, Long> {}
