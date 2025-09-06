package com.learning.anu3dev.dto;

import java.util.Collections;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// @NoArgsConstructor → allows creating empty object: new PollResponse().
@AllArgsConstructor
// @AllArgsConstructor → allows creating full object: new PollResponse(200, true, "OK").
public class PollApiResponse<T> {
    private int statusCode;
    private boolean success;
    private String successMessage;
    private boolean error;
    private String errorMessage;
    private List<T> data;

    // Factory method for success
    public static <T> PollApiResponse<T> success(String message, List<T> data) {
        return new PollApiResponse<>(200, true, message, false, "", data);
    }

    // Factory method for failure
    public static <T> PollApiResponse<T> failure(int statusCode, String message) {
        return new PollApiResponse<>(statusCode, false, "", true, message, Collections.emptyList());
    }
}