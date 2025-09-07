package com.learning.anu3dev.dto;

import lombok.Data;

@Data
public class OptionWithProgress {
  private Long optionId;
  private String optionText;
  private int count;
}
