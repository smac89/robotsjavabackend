package com.github.smac89.javabackend.repository

import com.github.smac89.javabackend.AbstractSpringUnitTest
import com.github.smac89.javabackend.domain.Robot
import org.jeasy.random.EasyRandom
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired

class RobotsRepositoryTest extends AbstractSpringUnitTest {
    @SpringBean
    private RobotsRepository repository = Stub()

    @Autowired
    private EasyRandom easyRandom

    def "will retrieve a robot by id"(int id) {
        given:
        repository.getRobotById(id) >> easyRandom.nextObject(Robot).toBuilder().id(id).build()

        when:
        def robot = repository.getRobotById(id)

        then:
        with(robot) {
            it.id == 34
        }

        where:
        id | _
        34 | _
    }
}
