package com.example.dbcsassignmentunittests.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class JwtException extends ServletException {
    public JwtException(String username, String message) {
        super(String.format("Login failed for [%s]: %s", username, message));
    }
}
