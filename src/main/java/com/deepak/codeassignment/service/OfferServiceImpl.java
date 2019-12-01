package com.deepak.codeassignment.service;


import com.deepak.codeassignment.model.Offer;
import com.deepak.codeassignment.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Setter
public class OfferServiceImpl implements OfferService {

    private OfferRepository offerRepository;

    @Override
    public List<Offer> getOffers() {

        log.debug("getOffers....");

        //Todo Actual implementation

        List<Offer> offerList = offerRepository.findAll();

        /*return Arrays.asList(Offer
                .builder()
                .product(Product.builder().productId(9L).productName("BedLamp").section("Furniture").build())
                .offerId(2222L)
                .offerStatus(OfferStatusEnum.LIVE)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(1))
                .build());*/

        return offerList;

    }

    @Override
    public Offer createOffer(Offer offer) {
        //Todo Actual implementation
        return null;

        //offerRepository.save()
    }

    @Override
    public Optional<Offer> getOfferByOfferId(long offerId) {
        return offerRepository.findById(offerId);
    }

    @Override
    public Offer updateOffer(Offer offerToBeCancelled) {
        return offerRepository.saveAndFlush(offerToBeCancelled);
    }
}
