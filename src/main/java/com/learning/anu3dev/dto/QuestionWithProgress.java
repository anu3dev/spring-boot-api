package com.learning.anu3dev.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuestionWithProgress {
  private Long questionId;
  private String questionTitle;
  private boolean isMultiSelect;
  private List<OptionWithProgress> options;
  private List<Long> selectedOptionIds; // user’s current picks for this question
}
