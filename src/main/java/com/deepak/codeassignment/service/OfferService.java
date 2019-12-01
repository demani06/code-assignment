package com.deepak.codeassignment.service;

import com.deepak.codeassignment.model.Offer;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    List<Offer> getOffers();

    Offer createOffer(Offer offer);

    Optional<Offer> getOfferByOfferId(long offerId);

    Offer updateOffer(Offer offerToBeCancelled);
}
