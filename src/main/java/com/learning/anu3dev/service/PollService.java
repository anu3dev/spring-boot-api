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

			if (pollReq.getPollId() == null) {
				// No pollId in request → create new poll
				poll = new Poll();
				poll.setPollTitle(pollReq.getPollTitle());
				poll.setActive(pollReq.isActive());
			} else {
				// if present in DB with requested pollId then just select
				 poll = pollRepo.findById(pollReq.getPollId())
						 .orElseGet(() -> {
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
				}
			}

			savedPolls.add(pollRepo.save(poll));
		}

		return savedPolls;
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