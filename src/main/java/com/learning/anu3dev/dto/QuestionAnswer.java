package com.learning.anu3dev.dto;

import java.util.List;

import lombok.Data;

@Data
public class QuestionAnswer {
  private Long questionId;
  private List<Long> optionIds;
}
