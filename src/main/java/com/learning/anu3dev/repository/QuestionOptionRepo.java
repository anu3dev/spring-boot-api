package com.learning.anu3dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learning.anu3dev.model.QuestionOption;

public interface QuestionOptionRepo extends JpaRepository<QuestionOption, Long> {
	@Modifying
	@Query("update QuestionOption o set o.count = o.count + 1 where o.id = :id")
	int increment(@Param("id") Long id);

	@Modifying
	@Query("update QuestionOption o set o.count = case when o.count > 0 then o.count - 1 else 0 end where o.id = :id")
	int decrement(@Param("id") Long id);
}
