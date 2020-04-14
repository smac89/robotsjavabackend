package com.github.smac89.javabackend.infrastructure;

import com.github.smac89.javabackend.domain.Robot;
import com.github.smac89.javabackend.domain.exception.RobotNotFoundException;
import com.github.smac89.javabackend.infrastructure.mapper.RobotsRecordMapper;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.github.smac89.generated.Tables.ROBOTS;

@Repository
@AllArgsConstructor
public class RobotsRepository implements com.github.smac89.javabackend.repository.RobotsRepository {
    private final DSLContext create;
    private final RobotsRecordMapper robotsRecordMapper;

    @Override
    public Robot getRobotById(Integer id) {
        return create.selectFrom(ROBOTS)
                     .where(ROBOTS.ID.eq(id))
                     .fetchOptional(robotsRecordMapper::map)
                     .orElseThrow(() -> new RobotNotFoundException(id));
    }

    @Override
    public List<Robot> findAllRobots() {
        return create.selectFrom(ROBOTS).fetch(robotsRecordMapper::map);
    }
}
