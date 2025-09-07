package com.learning.anu3dev.dto;

import java.util.List;

import lombok.Data;

@Data
public class PollWithProgress {
  private Long pollId;
  private String pollTitle;
  private boolean completed;
  private List<QuestionWithProgress> questions;
}
