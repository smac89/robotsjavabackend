package com.github.smac89.javabackend.domain.marshal;

import com.github.smac89.javabackend.controller.RobotsController;
import com.github.smac89.javabackend.domain.Robot;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RobotModelMarshaller implements RepresentationModelAssembler<Robot, EntityModel<Robot>> {
    @Override
    public EntityModel<Robot> toModel(Robot robot) {
        return new EntityModel<>(robot,
                                 linkTo(methodOn(RobotsController.class).getRobot(robot.getId())).withSelfRel());
    }
}
