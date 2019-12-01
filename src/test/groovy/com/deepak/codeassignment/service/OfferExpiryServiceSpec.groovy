package com.deepak.codeassignment.service

import com.deepak.codeassignment.model.CurrencyEnum
import com.deepak.codeassignment.model.Offer
import com.deepak.codeassignment.model.OfferStatusEnum
import com.deepak.codeassignment.model.Product
import com.deepak.codeassignment.repository.OfferRepository
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

class OfferExpiryServiceSpec extends Specification {

    private OfferExpireService offerExpireService

    private OfferRepository offerRepository

    Offer offer1
    Offer expiredOffer1

    def setup() {

        Product product1 = Product.builder()
                .productId(21999L)
                .productName("Wings of Fire")
                .section("Books")
                .build();

        // Create a LIVE Offer
        offer1 = Offer.builder()
                .offerDescription("New Year Sale")
                .offerId(3L)
                .currency(CurrencyEnum.GBP)
                .product(product1)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusHours(48))
                .offerStatus(OfferStatusEnum.LIVE)
                .build();

        expiredOffer1 = Offer.builder()
                .offerDescription("New Year Sale")
                .offerId(3L)
                .currency(CurrencyEnum.GBP)
                .product(product1)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusHours(48))
                .offerStatus(OfferStatusEnum.EXPIRED)
                .build();
        offerRepository = Stub(OfferRepository.class)
        //offerRepository.findAllWithEndDateBefore(LocalDateTime.now(), OfferStatusEnum.LIVE) >> Arrays.asList(offer1)
        offerRepository.findAllWithEndDateBefore(*_) >> Arrays.asList(offer1)
        offerRepository.saveAndFlush(offer1) >> expiredOffer1

        offerExpireService = new OfferExpireService(offerRepository)
    }

    @Unroll("test the schedule expire service")
    def "test the schedule expire service"() {

        given: "Get offers service method exists"

        when: "When I call the checkAndUpdateOffersIfExpired()"

        offerExpireService.checkAndUpdateOffersIfExpired()

        then: "I expect it to be completed successfully with no exceptions thrown"

        noExceptionThrown()
        //assert listOfOffers.size() == 1
    }


}
