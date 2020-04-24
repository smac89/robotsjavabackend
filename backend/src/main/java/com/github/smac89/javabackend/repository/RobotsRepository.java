package com.github.smac89.javabackend.repository;

import com.github.smac89.javabackend.domain.Robot;

import java.util.List;

public interface RobotsRepository {
    Robot getRobotById(Integer id);
    List<Robot> findAllRobots();
}
