package com.kbtg.bootcamp.posttest.exception;

import org.springframework.http.HttpStatus;

public class ApiExceptionResponse {
    private final String message;
    private final HttpStatus httpStatus;

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ApiExceptionResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
