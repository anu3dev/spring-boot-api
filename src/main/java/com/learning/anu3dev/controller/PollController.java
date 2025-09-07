package com.learning.anu3dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.anu3dev.dto.PollRequest;
import com.learning.anu3dev.dto.PollWithProgress;
import com.learning.anu3dev.dto.SubmitPollAnswersRequest;
import com.learning.anu3dev.dto.LoginRequest;
import com.learning.anu3dev.dto.LoginResponse;
import com.learning.anu3dev.model.Poll;
import com.learning.anu3dev.service.PollAnswerService;
import com.learning.anu3dev.service.PollService;

@RestController
@RequestMapping("poll")
public class PollController {

	@Autowired
	private PollService pollService;
	@Autowired
	private PollAnswerService pollAnswerService;

	@PostMapping("/add-polls")
	public ResponseEntity<LoginResponse<Poll>> addPolls(@RequestBody List<PollRequest> request) {
	    List<Poll> savedPolls = pollService.saveOrUpdatePolls(request);
	    return ResponseEntity.ok(LoginResponse.success("Action successful", savedPolls));
	}

	@GetMapping("/get-all-polls")
	public ResponseEntity<LoginResponse<Poll>> getAllPolls() {
		List<Poll> polls = pollService.getAllPolls();
		return ResponseEntity.ok(LoginResponse.success("Action successful", polls));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse<PollWithProgress>> pollLogin(@RequestBody LoginRequest request) {
	    LoginResponse<PollWithProgress> response = pollService.loginAndFetchEligiblePolls(request);
	    return ResponseEntity.ok(response);
	}
	
	@PostMapping("/polls/submit")
	  public ResponseEntity<LoginResponse<Void>> submit(
	      @RequestParam Long userId,
	      @RequestBody SubmitPollAnswersRequest req) {
	    pollAnswerService.submitPollAnswers(userId, req);
	    return ResponseEntity.ok(LoginResponse.success("Poll submitted successfully", List.of()));
	  }

	  @DeleteMapping("/polls/clear")
	  public ResponseEntity<LoginResponse<Void>> clear(
	      @RequestParam Long userId,
	      @RequestParam Long pollId) {
	    pollAnswerService.clearPollForUser(userId, pollId);
	    return ResponseEntity.ok(LoginResponse.success("Poll cleared successfully", List.of()));
	  }
}