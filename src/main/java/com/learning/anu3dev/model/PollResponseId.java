package com.learning.anu3dev.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class PollResponseId implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long questionId;
	private Long optionId;
}
