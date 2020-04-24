package com.github.smac89.javabackend.domain.error;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class ApiError {
    public final String errorMessage;
    @JsonIgnore
    public final HttpStatus status;

    @JsonProperty
    private int getStatusCode() {
        return status.value();
    }
}
