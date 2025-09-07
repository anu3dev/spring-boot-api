package com.learning.anu3dev.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learning.anu3dev.model.PollResponse;
import com.learning.anu3dev.model.PollResponseId;

public interface PollResponseRepo extends JpaRepository<PollResponse, PollResponseId> {
	List<PollResponse> findByUserIdAndQuestionIdIn(Long userId, Collection<Long> questionIds);
	List<PollResponse> findByUserIdAndQuestionPollId(Long userId, Long pollId);
	@Modifying @Query("delete from PollResponse r where r.user.id = :userId and r.question.poll.id = :pollId")
	void deleteAllForUserAndPoll(@Param("userId") Long userId, @Param("pollId") Long pollId);
}
