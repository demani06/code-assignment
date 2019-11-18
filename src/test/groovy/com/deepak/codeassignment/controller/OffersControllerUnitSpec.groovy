package com.deepak.codeassignment.controller

import com.deepak.codeassignment.utils.Endpoints
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get

@ContextConfiguration
@WebMvcTest
class OffersControllerUnitSpec extends Specification {

    @Autowired
    private MockMvc mockMvc


    @Unroll ("test the get offers endpoint")
    def "test the get offers endpoint"() {

        given: "Get offers end point exists"

        when: "When I call the end point offers"
        mockMvc.perform(get(Endpoints.OFFERS_ENDPOINT))
                .andExpect(status().isOk())

        then: "I expect it should return all offers"

    }

    @Unroll ("test the create offer endpoint")
    def "test the create the offers endpoint"() {

        given: "Get offers end point exists"

        when: "When I call the end point offers"
        mockMvc.perform(post(Endpoints.OFFERS_ENDPOINT))
                .andExpect(status().isOk())

        then: "I expect it should return all offers"

    }


}
