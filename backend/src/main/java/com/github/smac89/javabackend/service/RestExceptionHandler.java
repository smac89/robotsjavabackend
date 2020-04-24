package com.github.smac89.javabackend.service;

import com.github.smac89.javabackend.domain.error.ApiError;
import com.github.smac89.javabackend.infrastructure.exception.RobotNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RobotNotFoundException.class)
    ResponseEntity<ApiError> handleRobotNotFoundException(RobotNotFoundException ex) {
        return buildErrorResponse(new ApiError(ex.getMessage(), HttpStatus.NOT_FOUND));
    }

    private ResponseEntity<ApiError> buildErrorResponse(ApiError error) {
        return new ResponseEntity<>(error, error.status);
    }
}
