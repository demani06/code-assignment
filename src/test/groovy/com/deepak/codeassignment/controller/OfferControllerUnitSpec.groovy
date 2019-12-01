package com.deepak.codeassignment.controller

import com.deepak.codeassignment.model.CurrencyEnum
import com.deepak.codeassignment.model.Offer
import com.deepak.codeassignment.model.OfferStatusEnum
import com.deepak.codeassignment.model.Product
import com.deepak.codeassignment.repository.OfferRepository
import com.deepak.codeassignment.service.OfferService
import com.deepak.codeassignment.utils.Endpoints
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll
import spock.mock.DetachedMockFactory

import java.time.LocalDateTime

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [OfferController])
class OfferControllerUnitSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    @Shared
    OfferRepository offerRepository

    @Autowired
    OfferService offerService

    OfferController offerController

    def setup() {
        //Set up test data
        // Create a Product
        Product product1 = Product.builder()
                .productId(29990L)
                .productName("LG 43 Inch Full HD SMART TV ")
                .section("Electronics")
                .build();

        // Create a LIVE Offer
        Offer offer1 = Offer.builder()
                .offerDescription("Black Friday sale")
                .offerId(3L)
                .currency(CurrencyEnum.GBP)
                .product(product1)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusHours(48))
                .offerStatus(OfferStatusEnum.LIVE)
                .build();

        // LIVE offer when cancelled
        Offer offer1WithCancelledStatus = Offer.builder()
                .offerDescription("Black Friday sale")
                .offerId(3L)
                .currency(CurrencyEnum.GBP)
                .product(product1)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusHours(48))
                .offerStatus(OfferStatusEnum.CANCELLED)
                .build();
        offerService.getOffers() >> Arrays.asList(offer1)
        offerService.getOfferByOfferId(3) >> Optional.of(offer1)
        offerService.getOfferByOfferId(1) >> Optional.empty()

        offerService.updateOffer(offer1) >> offer1WithCancelledStatus
    }

    @Unroll("test the get offers endpoint")
    def "test the get offers endpoint"() {

        given: "Get offers end point exists"

        when: "When I call the end point offers"

        ResultActions result = mockMvc.perform(get(Endpoints.OFFERS_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

        String content = result.andReturn().getResponse().getContentAsString();

        println("content=" + content)

        then: "I expect it should return all offers"

        assert content.contains("\"offerStatus\":\"LIVE\",\"currency\":\"GBP\",\"product\":{\"productId\":29990,\"productName\":\"LG 43 Inch Full HD SMART TV \",\"section\":\"Electronics\"}}]")

    }

    @Unroll("test the get a specific offer endpoint - positive test case")
    def "test the get a specific offer endpoint - positive test case"() {

        given: "Get offer end point exists"

        when: "When I call the get offer end point "

        print("offerService =" + offerService)

        String GET_OFFER_ENDPOINT = Endpoints.OFFERS_WITH_ID_ENDPOINT.replace("{offerId}", "3")

        println("GET_OFFER_ENDPOINT = " + GET_OFFER_ENDPOINT)

        ResultActions result = mockMvc.perform(get(GET_OFFER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

        String content = result.andReturn().getResponse().getContentAsString();

        println("content=" + content)

        then: "I expect it should return an offer because it exists in DB"

        assert content.contains("\"offerStatus\":\"LIVE\",\"currency\":\"GBP\",\"product\":{\"productId\":29990,\"productName\":\"LG 43 Inch Full HD SMART TV \",\"section\":\"Electronics\"}}")

    }

    @Unroll("test the get a specific offer endpoint - negative test case for exception")
    def "test the get a specific offer endpoint - negative test case for exception"() {

        given: "Get offer end point exists"

        when: "When I call the get offer end point "

        print("offerService =" + offerService)

        String GET_OFFER_ENDPOINT = Endpoints.OFFERS_WITH_ID_ENDPOINT.replace("{offerId}", "1")

        println("GET_OFFER_ENDPOINT = " + GET_OFFER_ENDPOINT)

        ResultActions result = mockMvc.perform(get(GET_OFFER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())

        String content = result.andReturn().getResponse().getContentAsString();

        println("content=" + content)

        then: "I expect it should return an exception because it does not exist in DB"

        assert content.contains("\"errorMessage\":\"Offer Not found\",\"errorMessageDetails\":[\"Offer not found\"]")

    }

    @Unroll("test the cancel offer offer endpoint - negative test case for exception")
    def "test the cancel offer offer endpoint - negative test case for exception"() {

        given: "Cancel offer for PUT request end point exists"

        when: "When I call the cancel offer end point with PUT request"

        print("offerService =" + offerService)

        String CANCEL_OFFER_ENDPOINT = Endpoints.OFFERS_WITH_ID_ENDPOINT.replace("{offerId}", "1")

        ResultActions result = mockMvc.perform(put(CANCEL_OFFER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())

        String content = result.andReturn().getResponse().getContentAsString();

        println("content=" + content)

        then: "I expect it should return an exception because it does not exist in DB"

        assert content.contains("\"errorMessage\":\"Offer Not found\",\"errorMessageDetails\":[\"Offer not found\"]")

    }

    @Unroll("test the cancel offer offer endpoint - positive test case")
    def "test the cancel offer offer endpoint - positive test case"() {

        given: "Cancel offer end point with PUT request exists"

        when: "When I call the cancel offer end point with PUT request "

        print("offerService =" + offerService)

        String CANCEL_OFFER_ENDPOINT = Endpoints.OFFERS_WITH_ID_ENDPOINT.replace("{offerId}", "3")

        ResultActions result = mockMvc.perform(put(CANCEL_OFFER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

        String content = result.andReturn().getResponse().getContentAsString();

        then: "I expect it should update the record successfully because it does not exist in DB and it should update to Cancelled"

        assert content.contains("\"offerStatus\":\"CANCELLED\",\"currency\":\"GBP\",\"product\":{\"productId\":29990,\"productName\":\"LG 43 Inch Full HD SMART TV \",\"section\":\"Electronics\"}}")

    }

    /* TODO implementation of this endpoint
    @Unroll("test the create offer endpoint")
     def "test the create the offers endpoint"() {

         given: "Get offers end point exists"

         when: "When I call the end point offers"
         mockMvc.perform(post(Endpoints.OFFERS_ENDPOINT))
                 .andExpect(status().isOk())

         then: "I expect it should return all offers"

     }*/


    @TestConfiguration
    static class MockConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        OfferService offerService() {
            return detachedMockFactory.Stub(OfferService)
        }
    }


}
