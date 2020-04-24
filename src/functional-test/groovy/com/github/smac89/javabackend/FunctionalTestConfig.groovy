package com.github.smac89.javabackend

import com.github.smac89.javabackend.mock.ObjectMotherConfig
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import
import org.springframework.test.context.TestPropertySource

@Import(ObjectMotherConfig)
@TestConfiguration
@TestPropertySource("classpath:/function-test.properties")
class FunctionalTestConfig {
}
