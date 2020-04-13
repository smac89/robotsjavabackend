package com.github.smac89.javabackend.service;

import com.github.smac89.javabackend.domain.Robot;
import com.github.smac89.javabackend.domain.exception.RobotNotFoundException;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.github.smac89.generated.Tables.ROBOTS;

@Service
public class RobotsService {
    private final DSLContext create;

    public RobotsService(DSLContext create) {
        this.create = create;
    }

    @Transactional
    public Robot getRobot(Integer id) {
        Objects.requireNonNull(id, "Id cannot be null");
        return create.selectFrom(ROBOTS)
                     .where(ROBOTS.ID.eq(id))
                     .fetchOptionalInto(Robot.class)
                     .orElseThrow(() -> new RobotNotFoundException(id));
    }

    @Transactional
    public List<Robot> getRobots(int page, int count) {
        return create.selectFrom(ROBOTS).limit(count).fetchInto(Robot.class);
    }
}
