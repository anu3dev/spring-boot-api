package com.learning.anu3dev.dto;

import lombok.Data;

import java.util.List;

@Data
public class OpenAiApiPostPayload {
    public String model;
    public int max_tokens;
    public double temperature;
    public List<Message> messages;

    public static class Message {
        public String role;
        public String content;

        public Message(String role, String content){
            this.role = role;
            this.content = content;
        }
    }
}
