package com.learning.anu3dev.repository;

import com.learning.anu3dev.model.OpenAiApiHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpenAiApiRepo extends JpaRepository<OpenAiApiHistory, Long> {
    List<OpenAiApiHistory> findTop20BySessionIdOrderByIdDesc(String sessionId);
}