package com.learning.anu3dev.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PollApiResponse {
    private int statusCode;
    private boolean success;
    private String successMessage;
    private boolean error;
    private String errorMessage;

    // Wraps itself in a list
    public List<PollApiResponse> asList() {
        return List.of(this);
    }

    // Factory method for success
    public static PollApiResponse success(String message) {
        return new PollApiResponse(200, true, message, false, "");
    }

    // Factory method for failure
    public static PollApiResponse failure(int statusCode, String message) {
        return new PollApiResponse(statusCode, false, "", true, message);
    }
}