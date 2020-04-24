package com.github.smac89.javabackend.service;

import com.github.smac89.javabackend.domain.Robot;
import com.github.smac89.javabackend.repository.RobotsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@AllArgsConstructor
public class RobotsService {
    private final RobotsRepository repository;
    private final RestTemplate restTemplate;

    private static final String ROBOTS_IMAGE_URL_TEMPLATE = "https://robohash.org/%d?size=200x200";

    @Transactional
    public Robot getRobot(int id) {
        return repository.getRobotById(id);
    }

    @Transactional
    public List<Robot> getRobots() {
        return repository.findAllRobots();
    }

    public byte[] getRobotImage(int id) {
        return restTemplate.getForObject(String.format(ROBOTS_IMAGE_URL_TEMPLATE, id), byte[].class);
    }
}
