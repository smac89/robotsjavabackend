package com.github.smac89.javabackend.infrastructure.exception;

public class RobotNotFoundException extends RuntimeException {
    public RobotNotFoundException(Integer id) {
        super(String.format("Could not find a robot with the given id. {id=%d}", id));
    }
}
