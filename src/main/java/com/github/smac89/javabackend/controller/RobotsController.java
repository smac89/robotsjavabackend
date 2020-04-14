package com.github.smac89.javabackend.controller;

import com.github.smac89.javabackend.domain.Robot;
import com.github.smac89.javabackend.domain.marshal.RobotModelMarshaller;
import com.github.smac89.javabackend.service.RobotsService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/robots", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class RobotsController {
    private final RobotsService robotsService;
    private final RobotModelMarshaller robotModelMarshaller;

    @GetMapping(value = "/{id}")
    public EntityModel<Robot> getRobot(@PathVariable Integer id) {
        return robotModelMarshaller.toModel(robotsService.getRobot(id));
    }

    @GetMapping
    public CollectionModel<EntityModel<Robot>> getAllRobots() {
        return robotModelMarshaller.toCollectionModel(robotsService.getRobots(0, 0));
    }
}
