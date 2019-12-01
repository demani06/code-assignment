package com.deepak.codeassignment.service

import com.deepak.codeassignment.model.CurrencyEnum
import com.deepak.codeassignment.model.Offer
import com.deepak.codeassignment.model.OfferStatusEnum
import com.deepak.codeassignment.model.Product
import com.deepak.codeassignment.repository.OfferRepository
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDateTime

class OfferServiceUnitTestSpec extends Specification {

    private OfferRepository offerRepository

    private OfferService offerService

    Offer offer1
    Offer offer1WithCancelledStatus

    def setup() {
        //Set up test data
        // Create a Product
        Product product1 = Product.builder()
                .productId(29990L)
                .productName("LG 43 Inch Full HD SMART TV ")
                .section("Electronics")
                .build();

        // Create a LIVE Offer
        offer1 = Offer.builder()
                .offerDescription("Black Friday sale")
                .offerId(3L)
                .currency(CurrencyEnum.GBP)
                .product(product1)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusHours(48))
                .offerStatus(OfferStatusEnum.LIVE)
                .build();

        // LIVE offer when cancelled
        offer1WithCancelledStatus = Offer.builder()
                .offerDescription("Black Friday sale")
                .offerId(3L)
                .currency(CurrencyEnum.GBP)
                .product(product1)
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusHours(48))
                .offerStatus(OfferStatusEnum.CANCELLED)
                .build();

        offerRepository = Stub(OfferRepository.class)

        offerRepository.findAll() >> Arrays.asList(offer1)
        offerRepository.findById(3) >> Optional.of(offer1)
        offerRepository.findById(1) >> Optional.empty()

        offerRepository.saveAndFlush(offer1) >> offer1WithCancelledStatus

        offerService = new OfferServiceImpl(offerRepository)
    }

    @Unroll("test the get offers service")
    def "test the get offers service"() {

        given: "Get offers service method exists"

        when: "When I call the getOffers()"

        List<Offer> listOfOffers = offerService.getOffers();

        then: "I expect it should return all offers present in DB"

        assert listOfOffers.size() == 1
    }


    @Unroll("test the cancel offer service method ")
    def "test the cancel offer service method"() {

        given: "service method for cancel offer exists"

        when: "When I call the cancelOffer method"

        Offer cancelledOffer = offerService.cancelOffer(offer1);

        then: "I expect the status to be updated to cancelled"

        assert cancelledOffer.getOfferStatus() == OfferStatusEnum.CANCELLED
    }

    @Unroll("test the get offer service method - scenario offer id - #inputOfferId")
    def "test the get offer service method - scenario #inputOfferId"() {

        given: "service method for get getOfferByOfferId exists"

        when: "When I call the getOfferByOfferId method"

        Optional<Offer> offerOptional = offerService.getOfferByOfferId(inputOfferId);

        then: "I expect the output as per the data table"

        assert offerOptional.isPresent() == expectedOutput

        where:

        inputOfferId | expectedOutput
        3            | true
        1            | false


    }


}
