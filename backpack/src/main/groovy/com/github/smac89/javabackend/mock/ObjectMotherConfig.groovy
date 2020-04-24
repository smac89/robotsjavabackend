package com.github.smac89.javabackend.mock

import groovy.transform.CompileStatic
import org.jeasy.random.EasyRandom
import org.jeasy.random.EasyRandomParameters
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource(value = "classpath:easy-random.properties", ignoreResourceNotFound = true)
@CompileStatic
class ObjectMotherConfig {
    @Bean
    EasyRandom easyRandom(EasyRandomParameters parameters) {
        return new EasyRandom(parameters)
    }

    @Bean
    @ConfigurationProperties("org.jeasy")
    EasyRandomParameters easyRandomParameters() {
        return new EasyRandomParameters()
    }
}
