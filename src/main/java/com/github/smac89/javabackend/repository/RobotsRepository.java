package com.github.smac89.javabackend.repository;

import com.github.smac89.javabackend.domain.model.Robot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RobotsRepository extends JpaRepository<Robot, Integer> {}
