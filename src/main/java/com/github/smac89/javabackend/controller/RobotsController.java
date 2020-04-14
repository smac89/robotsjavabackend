package com.github.smac89.javabackend.controller;

import com.github.smac89.javabackend.domain.Robot;
import com.github.smac89.javabackend.domain.marshal.RobotModelMarshaller;
import com.github.smac89.javabackend.service.RobotsService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/robots")
@AllArgsConstructor
public class RobotsController {
    private final RobotsService robotsService;
    private final RobotModelMarshaller robotModelMarshaller;

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Robot> getRobot(@PathVariable int id) {
        return robotModelMarshaller.toModel(robotsService.getRobot(id));
    }

    @GetMapping(value = "/{id}.png", produces = MediaType.IMAGE_PNG_VALUE)
    public ByteArrayResource getRobotImage(@PathVariable int id) {
        return new ByteArrayResource(robotsService.getRobotImage(id));
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Robot>> getAllRobots() {
        return robotModelMarshaller.toCollectionModel(robotsService.getRobots());
    }
}
