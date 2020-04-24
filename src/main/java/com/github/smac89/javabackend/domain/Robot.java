package com.github.smac89.javabackend.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Robot {
    Integer id;
    String name;
    String username;
    String email;
}
