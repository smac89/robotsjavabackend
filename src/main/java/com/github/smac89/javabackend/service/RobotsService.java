package com.github.smac89.javabackend.service;

import com.github.smac89.javabackend.domain.RobotNotFoundException;
import com.github.smac89.javabackend.domain.model.Robot;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static org.jooq.generated.tables.Robots.ROBOTS;

@Service
public class RobotsService {
    private final DSLContext dslContext;

    public RobotsService(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Transactional
    public Robot getRobot(Integer id) {
        Objects.requireNonNull(id, "Id cannot be null");
        return dslContext.selectFrom(ROBOTS)
                         .where(ROBOTS.ID.eq(id))
                         .limit(1)
                         .fetchStreamInto(Robot.class)
                         .findFirst()
                         .orElseThrow(() -> new RobotNotFoundException(id));
    }

    @Transactional
    public List<Robot> getRobots(int page, int count) {
        return dslContext.selectFrom(ROBOTS).limit(count).fetchInto(Robot.class);
    }
}
