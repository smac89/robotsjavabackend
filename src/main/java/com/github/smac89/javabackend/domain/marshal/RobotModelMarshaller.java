package com.github.smac89.javabackend.domain.marshal;

import com.github.smac89.javabackend.controller.RobotsController;
import com.github.smac89.javabackend.domain.Robot;
import org.springframework.core.ResolvableType;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RobotModelMarshaller extends RepresentationModelAssemblerSupport<Robot, EntityModel<Robot>> {

    public RobotModelMarshaller() {
        //noinspection unchecked
        super(RobotsController.class,
              (Class<EntityModel<Robot>>)
                      Objects.requireNonNull(ResolvableType.forClassWithGenerics(EntityModel.class, Robot.class)
                                                           .resolve()));
    }

    @Override
    public EntityModel<Robot> toModel(Robot robot) {
        return new EntityModel<>(robot,
                                 linkTo(methodOn(RobotsController.class).getRobot(robot.getId())).withSelfRel());
    }

    @Override
    public CollectionModel<EntityModel<Robot>> toCollectionModel(Iterable<? extends Robot> entities) {
        return super.toCollectionModel(entities)
                    .add(linkTo(methodOn(RobotsController.class).getAllRobots()).withSelfRel());
    }
}
