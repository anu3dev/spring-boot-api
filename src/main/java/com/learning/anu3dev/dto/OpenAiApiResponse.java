package com.learning.anu3dev.dto;

import java.util.List;

public class OpenAiApiResponse {
    public List<OpenAiChoice> choices;

    public static class OpenAiChoice {
        public OpenAiMessage message;
    }

    public static class OpenAiMessage {
        public String content;
    }
}
