package com.deepak.codeassignment.service;


import com.deepak.codeassignment.model.Offer;
import com.deepak.codeassignment.repository.OfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OfferServiceImpl implements OfferService {

    private OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    @Override
    public List<Offer> getOffers() {

        log.debug("getOffers....");

        List<Offer> offerList = offerRepository.findAll();

        return offerList;
    }

   /* @Override
    public Offer createOffer(Offer offer) {
        //Todo Actual implementation
        return null;

        //offerRepository.save()
    }*/

    @Override
    public Optional<Offer> getOfferByOfferId(long offerId) {
        return offerRepository.findById(offerId);
    }

    @Override
    public Offer cancelOffer(Offer offerToBeCancelled) {
        return offerRepository.saveAndFlush(offerToBeCancelled);
    }
}
