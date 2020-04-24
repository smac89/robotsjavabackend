package com.github.smac89.javabackend

import com.github.smac89.javabackend.mock.ObjectMotherConfig
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Specification

//https://www.baeldung.com/spring-boot-testing
@RunWith(SpringRunner)
@ContextConfiguration(classes = ObjectMotherConfig)
abstract class AbstractSpringUnitTest extends Specification {
}
