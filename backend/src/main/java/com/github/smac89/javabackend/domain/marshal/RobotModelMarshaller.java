package com.github.smac89.javabackend.domain.marshal;

import com.github.smac89.javabackend.controller.RobotsController;
import com.github.smac89.javabackend.domain.Robot;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RobotModelMarshaller implements SimpleRepresentationModelAssembler<Robot> {

    @Override
    public void addLinks(EntityModel<Robot> resource) {
        var robot = Objects.requireNonNull(resource.getContent());
        resource.add(linkTo(methodOn(RobotsController.class).getRobot(robot.getId())).withSelfRel());
        resource.add(linkTo(methodOn(RobotsController.class).getRobotImage(robot.getId())).withRel("image"));
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Robot>> resources) {
        resources.add(linkTo(methodOn(RobotsController.class).getAllRobots()).withSelfRel());
    }
}
