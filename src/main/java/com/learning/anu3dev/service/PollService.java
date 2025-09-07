package com.learning.anu3dev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.anu3dev.dto.LoginRequest;
import com.learning.anu3dev.dto.LoginResponse;
import com.learning.anu3dev.dto.OptionWithProgress;
import com.learning.anu3dev.dto.PollRequest;
import com.learning.anu3dev.dto.PollWithProgress;
import com.learning.anu3dev.dto.QuestionRequest;
import com.learning.anu3dev.dto.QuestionWithProgress;
import com.learning.anu3dev.model.Poll;
import com.learning.anu3dev.model.User;
import com.learning.anu3dev.model.UserPollStatus;
import com.learning.anu3dev.model.PollQuestion;
import com.learning.anu3dev.model.PollResponse;
import com.learning.anu3dev.model.QuestionOption;
import com.learning.anu3dev.repository.PollRepo;
import com.learning.anu3dev.repository.UserPollStatusRepo;
import com.learning.anu3dev.repository.UserRepo;
import com.learning.anu3dev.repository.PollResponseRepo;

import jakarta.transaction.Transactional;

@Service
public class PollService {

	@Autowired
	private PollRepo pollRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserPollStatusRepo userPollStatusRepo;
	@Autowired
	private PollResponseRepo pollResponseRepo;

	@Transactional
	public LoginResponse<PollWithProgress> loginAndFetchEligiblePolls(LoginRequest request) {
	    User user = userRepo.findByEmail(request.getEmail()).orElseGet(() -> {
	        User newUser = new User();
	        newUser.setEmail(request.getEmail().trim().toLowerCase());
	        return userRepo.save(newUser);
	    });

	    // Active polls
	    List<Poll> activePolls = pollRepo.findByIsActiveTrueOrderByCreatedAtDesc();

	    List<PollWithProgress> payload = activePolls.stream().map(p -> {
	        boolean completed = userPollStatusRepo
	                .findByUserIdAndPollId(user.getId(), p.getId())
	                .map(UserPollStatus::isCompleted).orElse(false);

	        List<Long> qIds = p.getQuestions().stream().map(PollQuestion::getId).toList();

	        List<PollResponse> existing = qIds.isEmpty()
	                ? List.of()
	                : pollResponseRepo.findByUserIdAndQuestionIdIn(user.getId(), qIds);

	        Map<Long, List<Long>> selectedByQuestion = existing.stream()
	                .collect(Collectors.groupingBy(r -> r.getQuestion().getId(),
	                        Collectors.mapping(r -> r.getOption().getId(), Collectors.toList())));

	        PollWithProgress dto = new PollWithProgress();
	        dto.setPollId(p.getId());
	        dto.setPollTitle(p.getPollTitle());
	        dto.setCompleted(completed);

	        List<QuestionWithProgress> qDtos = p.getQuestions().stream().map(q -> {
	            QuestionWithProgress qdto = new QuestionWithProgress();
	            qdto.setQuestionId(q.getId());
	            qdto.setQuestionTitle(q.getQuestionTitle());
	            qdto.setMultiSelect(q.isMultiSelect());
	            qdto.setSelectedOptionIds(selectedByQuestion.getOrDefault(q.getId(), List.of()));

	            List<OptionWithProgress> odtos = q.getOptions().stream().map(o -> {
	                OptionWithProgress od = new OptionWithProgress();
	                od.setOptionId(o.getId());
	                od.setOptionText(o.getOptionText());
	                od.setCount(o.getCount());
	                return od;
	            }).toList();

	            qdto.setOptions(odtos);
	            return qdto;
	        }).toList();

	        dto.setQuestions(qDtos);
	        return dto;
	    }).toList();

	    return LoginResponse.success("Action successful", payload);
	}
	
	public List<Poll> saveOrUpdatePolls(List<PollRequest> pollRequests) {
		List<Poll> savedPolls = new ArrayList<>();

		for (PollRequest pollReq : pollRequests) {
			Poll poll;

			if (pollReq.getPollId() == null) {
				// No pollId in request → create new poll
				poll = new Poll();
				poll.setPollTitle(pollReq.getPollTitle());
				poll.setActive(pollReq.isActive());
			} else {
				// if present in DB with requested pollId then just select
				poll = pollRepo.findById(pollReq.getPollId()).orElseGet(() -> {
					// else create new poll
					Poll newPoll = new Poll();
					newPoll.setPollTitle(pollReq.getPollTitle());
					newPoll.setActive(pollReq.isActive());
					return newPoll;
				});
			}

			List<PollQuestion> existingQuestions = poll.getQuestions();

			for (QuestionRequest questionReq : pollReq.getQuestions()) {
				// check if question already exists by title
				boolean questionExists = existingQuestions.stream().anyMatch(q -> q.getQuestionTitle() != null
						&& q.getQuestionTitle().equalsIgnoreCase(questionReq.getQuestionTitle()));

				if (!questionExists && questionReq.getQuestionTitle() != null) {
					PollQuestion pollQuestion = new PollQuestion();
					pollQuestion.setQuestionTitle(questionReq.getQuestionTitle());
					pollQuestion.setMultiSelect(questionReq.isMultiSelect());
					pollQuestion.setActive(questionReq.isActive());
					pollQuestion.setPoll(poll);

					// attach options
					for (String optionText : questionReq.getOptions()) {
						QuestionOption option = new QuestionOption();
						option.setOptionText(optionText);
						option.setQuestionId(pollQuestion);
						pollQuestion.getOptions().add(option);
					}

					poll.getQuestions().add(pollQuestion);

					savedPolls.add(pollRepo.save(poll));
				}
			}
		}

		return savedPolls;
	}

	public List<Poll> getAllPolls() {
		return pollRepo.findAll();
	}

	public List<Poll> getPolls() {
		List<Poll> activePolls = pollRepo.findAll().stream().filter(Poll::isActive).toList();
		activePolls.forEach(poll -> {
			List<PollQuestion> activeQuestions = poll.getQuestions().stream().filter(PollQuestion::isActive).toList();
			poll.setQuestions(activeQuestions);
		});
		return activePolls;
	}
}