package com.github.smac89.javabackend.config.jooq;

import org.jooq.Record;
import org.jooq.RecordMapper;
import org.jooq.RecordMapperProvider;
import org.jooq.RecordType;
import org.jooq.impl.DefaultRecordMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JooqRecordMapperProvider implements RecordMapperProvider {
    private final Map<String, RecordMapper<? extends Record, ?>> recordMappers;

    public JooqRecordMapperProvider(Map<String, RecordMapper<? extends Record, ?>> recordMappers) {
        this.recordMappers = recordMappers;
    }

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    @Cacheable("recordMappers")
    @Override
    public <R extends Record, E> RecordMapper<R, E> provide(RecordType<R> recordType, Class<? extends E> type) {
        return recordMappers.values().stream().filter(rm -> {
            var mapResolver = ResolvableType.forClass(rm.getClass()).as(RecordMapper.class);
            var recordResolver = ResolvableType.forClass(recordType.getClass());

            /*TODO Can we have this choose the best mapper rather than the first one that somewhat matches?*/
            return mapResolver.resolveGeneric(0).isAssignableFrom(recordResolver.resolveGeneric(0)) &&
                    mapResolver.resolveGeneric(1).isAssignableFrom(type);
        }).map(rm -> (RecordMapper<R, E>) rm).findFirst().orElseGet(() ->
                new DefaultRecordMapper<>(recordType, type));
    }
}
