package com.github.smac89.javabackend.domain.exception;

public class RobotNotFoundException extends RuntimeException {
    public RobotNotFoundException(Integer id) {
        super(String.format("Could not find a robot with the given id: %d", id));
    }
}
