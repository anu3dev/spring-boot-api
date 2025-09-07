package com.learning.anu3dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.anu3dev.model.UserPollStatus;

public interface UserPollStatusRepo extends JpaRepository<UserPollStatus, Long> {
	List<UserPollStatus> findByUserIdAndCompletedFalse(Long userId);
	Optional<UserPollStatus> findByUserIdAndPollId(Long userId, Long pollId);
}
