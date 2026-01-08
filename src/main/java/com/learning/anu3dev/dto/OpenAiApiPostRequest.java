package com.learning.anu3dev.dto;

import lombok.Data;

@Data
public class OpenAiApiPostRequest {
    private String sessionId;
    private String query;

    public String getQuery() {
        return query;
    }

    public String getSessionId() {
        return sessionId;
    }
}
