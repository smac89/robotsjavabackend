package com.github.smac89.javabackend.controller

import com.github.smac89.javabackend.FunctionalTestConfig
import com.github.smac89.javabackend.domain.Robot
import com.github.smac89.javabackend.domain.marshal.RobotModelMarshaller
import com.github.smac89.javabackend.infrastructure.exception.RobotNotFoundException
import com.github.smac89.javabackend.service.RobotsService
import org.hamcrest.Matchers
import org.jeasy.random.EasyRandom
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll

import java.util.stream.Collectors

import static org.hamcrest.Matchers.*
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@Title("Robots Controller Tests")
@WebMvcTest(controllers = RobotsController, includeFilters = [
        @ComponentScan.Filter(value = RobotModelMarshaller, type = ASSIGNABLE_TYPE)
])
@ContextConfiguration(classes = FunctionalTestConfig)
class RobotsControllerTest extends Specification {

    @Autowired
    private MockMvc mvc

    @Autowired
    private EasyRandom easyRandom

    @SpringBean
    private RobotsService robotsService = Stub()

    def "/robots: will return all robots"() {
        given:
        robotsService.getRobots() >> easyRandom.objects(Robot, 4).collect(Collectors.toList())

        when:
        def result = mvc.perform(get("/robots"))

        then:
        result.andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("_embedded.robotList", hasSize(4)))
    }

    @Unroll
    def "/robots/#id: will return a robot with id = #id"(int id) {
        given:
        robotsService.getRobot(id) >> easyRandom.nextObject(Robot).toBuilder().id(id).build()

        when:
        def result = mvc.perform(get("/robots/$id"))

        then:
        result.andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath('$.id', Matchers.is(3)))

        where:
        id | _
        3  | _
    }

    @Unroll
    def "/robots/#id: will throw exception if no robot with id = #id"(int id) {
        given:
        robotsService.getRobot(id) >> { throw new RobotNotFoundException(3) }

        when:
        def result = mvc.perform(get("/robots/$id"))

        then:
        result.andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath('$.errorMessage', not(emptyOrNullString())))

        where:
        id | _
        3  | _
    }

    def '/robots/id.png: will return an image'(int id) {
        given:
        robotsService.getRobotImage(id) >> new byte[1]

        when:
        def result = mvc.perform(get("/robots/${id}.png"))

        then:
        result.andExpect(status().isOk())
                .andExpect(content().bytes(new byte[1]))

        where:
        id | _
        5  | _
    }
}
