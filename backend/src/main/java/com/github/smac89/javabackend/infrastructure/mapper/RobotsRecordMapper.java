package com.github.smac89.javabackend.infrastructure.mapper;

import com.github.smac89.generated.tables.records.RobotsRecord;
import com.github.smac89.javabackend.domain.Robot;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RobotsRecordMapper {
    Robot toRobot(RobotsRecord record);
}
