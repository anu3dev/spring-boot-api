package com.learning.anu3dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.anu3dev.dto.PollRequest;
import com.learning.anu3dev.dto.LoginRequest;
import com.learning.anu3dev.dto.LoginResponse;
import com.learning.anu3dev.model.Poll;
import com.learning.anu3dev.service.PollService;

@RestController
@RequestMapping("poll")
public class PollController {

	@Autowired
	private PollService pollService;

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
	public ResponseEntity<LoginResponse<Poll>> pollLogin(@RequestBody LoginRequest request) {
		List<Poll> polls = pollService.loginAndFetchEligiblePolls(request);
		//List<Poll> polls = pollService.getPolls();
		return ResponseEntity.ok(LoginResponse.success("Action successful", polls));
	}
}