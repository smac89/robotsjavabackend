package com.github.smac89.javabackend.infrastructure.marshal;

import com.github.smac89.javabackend.domain.model.Robot;
import org.jooq.RecordMapper;
import org.jooq.generated.tables.records.RobotsRecord;
import org.springframework.stereotype.Component;

@Component
public class RobotsRecordMapper implements RecordMapper<RobotsRecord, Robot> {

    @Override
    public Robot map(RobotsRecord record) {
        return Robot.builder()
                    .id(record.getId())
                    .username(record.getUsername())
                    .name(record.getName())
                    .email(record.getEmail())
                    .build();
    }
}
