package com.learning.anu3dev.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.anu3dev.dto.LoginResponse;
import com.learning.anu3dev.dto.QuestionAnswer;
import com.learning.anu3dev.dto.SubmitPollAnswersRequest;
import com.learning.anu3dev.model.Poll;
import com.learning.anu3dev.model.PollQuestion;
import com.learning.anu3dev.model.PollResponse;
import com.learning.anu3dev.model.PollResponseId;
import com.learning.anu3dev.model.QuestionOption;
import com.learning.anu3dev.model.User;
import com.learning.anu3dev.model.UserPollStatus;
import com.learning.anu3dev.repository.PollRepo;
import com.learning.anu3dev.repository.PollResponseRepo;
import com.learning.anu3dev.repository.QuestionOptionRepo;
import com.learning.anu3dev.repository.UserPollStatusRepo;
import com.learning.anu3dev.repository.UserRepo;

import jakarta.transaction.Transactional;

@Service
public class PollAnswerService {
	@Autowired
	private PollResponseRepo pollResponseRepo;
	@Autowired
	private QuestionOptionRepo questionOptionRepo;
	@Autowired
	private UserPollStatusRepo userPollStatusRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PollRepo pollRepo;

	@Transactional
	public LoginResponse<Void> submitPollAnswers(Long userId, SubmitPollAnswersRequest req) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));
		Poll poll = pollRepo.findById(req.getPollId())
				.orElseThrow(() -> new IllegalArgumentException("Poll not found: " + req.getPollId()));

		// Collect existing responses for this user & poll
		List<PollResponse> existing = pollResponseRepo.findByUserIdAndQuestionPollId(userId, poll.getId());

		// Build maps
		Map<Long, Set<Long>> existingByQ = existing.stream().collect(Collectors.groupingBy(r -> r.getQuestion().getId(),
				Collectors.mapping(r -> r.getOption().getId(), Collectors.toSet())));

		// Desired target selections from request
		Map<Long, Set<Long>> targetByQ = req.getAnswers().stream()
				.collect(Collectors.toMap(QuestionAnswer::getQuestionId, a -> new HashSet<>(a.getOptionIds())));

		// For each question in poll, compute add/remove deltas
		for (PollQuestion q : poll.getQuestions()) {
			Set<Long> oldSel = existingByQ.getOrDefault(q.getId(), Set.of());
			Set<Long> newSel = targetByQ.getOrDefault(q.getId(), Set.of());

			// additions
			Set<Long> toAdd = new HashSet<>(newSel);
			toAdd.removeAll(oldSel);

			// removals
			Set<Long> toRemove = new HashSet<>(oldSel);
			toRemove.removeAll(newSel);

			// apply additions
			for (Long optId : toAdd) {
				QuestionOption opt = findOption(q, optId);
				PollResponse r = new PollResponse();
				PollResponseId id = new PollResponseId();
				id.setUserId(user.getId());
				id.setQuestionId(q.getId());
				id.setOptionId(opt.getId());
				r.setId(id);
				r.setUser(user);
				r.setQuestion(q);
				r.setOption(opt);
				pollResponseRepo.save(r);
				questionOptionRepo.increment(opt.getId()); // atomic increment
			}

			// apply removals
			for (Long optId : toRemove) {
				PollResponseId id = new PollResponseId();
				id.setUserId(user.getId());
				id.setQuestionId(q.getId());
				id.setOptionId(optId);
				pollResponseRepo.deleteById(id);
				questionOptionRepo.decrement(optId);
			}
		}

		// Mark poll completed if ALL questions have ≥1 selection
		boolean allAnswered = poll.getQuestions().stream()
				.allMatch(q -> !targetByQ.getOrDefault(q.getId(), Set.of()).isEmpty());

		UserPollStatus status = userPollStatusRepo.findByUserIdAndPollId(userId, poll.getId()).orElseGet(() -> {
			UserPollStatus s = new UserPollStatus();
			s.setUser(user);
			s.setPoll(poll);
			return s;
		});

		if (allAnswered) {
			status.setCompleted(true);
			status.setCompletedAt(LocalDateTime.now());
		} else {
			status.setCompleted(false);
			status.setCompletedAt(null);
		}
		userPollStatusRepo.save(status);

		return LoginResponse.success("Poll submitted successfully", List.of());
	}

	private QuestionOption findOption(PollQuestion q, Long optionId) {
		return q.getOptions().stream().filter(o -> o.getId().equals(optionId)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Option not in question"));
	}

	@Transactional
	public void clearPollForUser(Long userId, Long pollId) {
		// load existing selections to decrement counts
		List<PollResponse> existing = pollResponseRepo.findByUserIdAndQuestionPollId(userId, pollId);
		for (PollResponse r : existing) {
			questionOptionRepo.decrement(r.getOption().getId());
		}
		pollResponseRepo.deleteAllForUserAndPoll(userId, pollId);

		// mark status incomplete
		userPollStatusRepo.findByUserIdAndPollId(userId, pollId).ifPresent(s -> {
			s.setCompleted(false);
			s.setCompletedAt(null);
			userPollStatusRepo.save(s);
		});
	}
}
