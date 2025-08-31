package com.learning.anu3dev.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.anu3dev.dto.PollRequest;
import com.learning.anu3dev.dto.QuestionRequest;
import com.learning.anu3dev.model.Poll;
import com.learning.anu3dev.model.PollQuestion;
import com.learning.anu3dev.model.QuestionOption;
import com.learning.anu3dev.repository.PollRepository;

@Service
public class PollService {

	@Autowired
	private PollRepository pollRepo;

	public List<Poll> saveOrUpdatePolls(List<PollRequest> pollRequests) {
		List<Poll> savedPolls = new ArrayList<>();

		for (PollRequest pollReq : pollRequests) {
			Poll poll;
			
			// Check if poll exists by ID
			if (pollReq.getPollId() != null) {
				poll = pollRepo.findById(pollReq.getPollId()).orElse(new Poll());
			} else {
				poll = new Poll();
			}

			poll.setPollTitle(pollReq.getPollTitle());
			poll.setActive(pollReq.isActive());

			List<PollQuestion> existingQuestions = poll.getQuestions();

			for (QuestionRequest questionReq : pollReq.getQuestions()) {
				// check if question already exists by title
				boolean questionExists = existingQuestions != null && existingQuestions.stream()
						.anyMatch(q -> q.getQuestion().equalsIgnoreCase(questionReq.getQuestion()));

				if (!questionExists) {
					PollQuestion pollQuestion = new PollQuestion();
					pollQuestion.setQuestion(questionReq.getQuestion());
					pollQuestion.setMultiSelect(questionReq.isMultiSelect());
					pollQuestion.setActive(questionReq.isActive());
					pollQuestion.setPoll(poll);
					
					// attach options
					for (String optionText : questionReq.getOptions()) {
						QuestionOption option = new QuestionOption();
						option.setOptionText(optionText);
						option.setQuestion(pollQuestion);
						pollQuestion.getOptions().add(option);
					}
										
					poll.getQuestions().add(pollQuestion);
				}
			}

			savedPolls.add(pollRepo.save(poll));
		}

		return savedPolls;
	}
}