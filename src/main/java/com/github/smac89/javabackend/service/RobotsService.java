package com.github.smac89.javabackend.service;

import com.github.smac89.javabackend.domain.Robot;
import com.github.smac89.javabackend.repository.RobotsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RobotsService {
    private final RobotsRepository repository;

    @Transactional
    public Robot getRobot(Integer id) {
        Objects.requireNonNull(id, "Id cannot be null");
        return repository.getRobotById(id);
    }

    @Transactional
    public List<Robot> getRobots(int page, int count) {
        return repository.findAllRobots();
    }
}
