package com.learning.anu3dev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("poll")
@RestController
public class PollController {
	@GetMapping("/get-polls")
	public String getPolls() {
		return "Poll";
	}
}
