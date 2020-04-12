package com.github.smac89.javabackend.domain.model;

import javax.persistence.Entity;

@Entity
public class Robot {
    private Integer id;
    private String name;
    private String username;
    private String email;

    public Integer getId() {
        return id;
    }
}
