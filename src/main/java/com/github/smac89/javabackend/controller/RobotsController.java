package com.github.smac89.javabackend.controller;

import com.github.smac89.javabackend.domain.marshal.RobotModelMarshaller;
import com.github.smac89.javabackend.domain.Robot;
import com.github.smac89.javabackend.service.RobotsService;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/robots")
public class RobotsController {
    private final RobotsService robotsService;
    private final RobotModelMarshaller robotModelMarshaller;

    public RobotsController(RobotsService robotsService,
                            RobotModelMarshaller robotModelMarshaller) {
        this.robotsService = robotsService;
        this.robotModelMarshaller = robotModelMarshaller;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public EntityModel<Robot> getRobot(@PathVariable Integer id) {
        return robotModelMarshaller.toModel(robotsService.getRobot(id));
    }
}
