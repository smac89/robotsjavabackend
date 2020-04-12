package com.github.smac89.javabackend.service;

import com.github.smac89.javabackend.domain.RobotNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RobotNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(RobotNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String robotNotFoundHandler(RobotNotFoundException ex) {
        return ex.getMessage();
    }
}
