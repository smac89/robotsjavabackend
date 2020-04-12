package com.github.smac89.javabackend.service;

import com.github.smac89.javabackend.domain.RobotNotFoundException;
import com.github.smac89.javabackend.domain.model.Robot;
import com.github.smac89.javabackend.repository.RobotsRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RobotsService {
    private final RobotsRepository robotsRepository;

    public RobotsService(RobotsRepository robotsRepository) {
        this.robotsRepository = robotsRepository;
    }

    public Robot getRobot(Integer id) {
        return robotsRepository.findById(Objects.requireNonNull(id, "Id cannot be null"))
                               .orElseThrow(() -> new RobotNotFoundException(id));
    }

    public List<Robot> getRobots(int page, int count) {
        return robotsRepository.findAll(PageRequest.of(page, count)).getContent();
    }
}
