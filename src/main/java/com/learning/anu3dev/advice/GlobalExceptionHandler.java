package com.learning.anu3dev.advice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.learning.anu3dev.dto.PollApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<List<PollApiResponse>> handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(PollApiResponse.failure(HttpStatus.METHOD_NOT_ALLOWED.value(),
                        "Invalid request method: " + ex.getMethod()).asList());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<List<PollApiResponse>> handleNotFound(NoHandlerFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(PollApiResponse.failure(HttpStatus.NOT_FOUND.value(),
                        "Endpoint not found: " + ex.getRequestURL()).asList());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<PollApiResponse>> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(PollApiResponse.failure(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Unexpected error: " + ex.getMessage()).asList());
    }
}