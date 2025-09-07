package com.learning.anu3dev.dto;

import java.util.List;

import lombok.Data;

@Data
public class SubmitPollAnswersRequest {
  private Long pollId;
  // For each question, the *full* set of selected optionIds (we’ll diff vs existing)
  private List<QuestionAnswer> answers;
}
