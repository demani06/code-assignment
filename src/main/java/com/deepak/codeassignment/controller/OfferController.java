package com.deepak.codeassignment.controller;

import com.deepak.codeassignment.exception.OfferNotFoundException;
import com.deepak.codeassignment.model.Offer;
import com.deepak.codeassignment.model.OfferStatusEnum;
import com.deepak.codeassignment.service.OfferService;
import com.deepak.codeassignment.utils.Endpoints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/*
 * This class would have endpoints w.r.t dealing with offers like creating offer, get an offer, cancel an offer
 * */

@RestController
@Slf4j
public class OfferController {

    public static final String OFFER_NOT_FOUND = "Offer not found";
    private OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping(Endpoints.OFFERS_ENDPOINT)
    public List<Offer> getOffers() {
        log.debug("Getting all offers");
        return offerService.getOffers();
    }

    @GetMapping(Endpoints.OFFERS_WITH_ID_ENDPOINT)
    public ResponseEntity<Offer> getOfferById(@PathVariable long offerId) throws OfferNotFoundException {
        log.debug("Getting offer offers by offer id = {}", offerId);
        //retrieve offer by offer id
        Optional<Offer> offerByOfferId = offerService.getOfferByOfferId(offerId);
        if (offerByOfferId.isPresent()) {
            return ResponseEntity.ok(offerByOfferId.get());
        } else {
            //throw OfferNotFound Exception
            throw new OfferNotFoundException(OFFER_NOT_FOUND);
        }
    }


   /* @PostMapping(Endpoints.OFFERS_ENDPOINT)
    public ResponseEntity createAnOffer( @Valid @RequestBody Offer offer,) {
        //Todo real implementation
        log.debug("Creating offer ....");
        offerService.createOffer();

        return

    }*/

    @PutMapping(Endpoints.OFFERS_WITH_ID_ENDPOINT)
    public ResponseEntity cancelOffer(@PathVariable long offerId) throws OfferNotFoundException {
        log.info("Retrieving offer .... for Offer id = {}", offerId);

        //TODO - validate offer id
        //retrieve offer by offer id
        Optional<Offer> offerByOfferId = offerService.getOfferByOfferId(offerId);
        if (offerByOfferId.isPresent()) {
            //Update the status to CANCELLED
            Offer offerToBeCancelled = offerByOfferId.get();
            offerToBeCancelled.setOfferStatus(OfferStatusEnum.CANCELLED);
            //TODO - update the modified date, modified user info

            final Offer updatedOffer = offerService.cancelOffer(offerToBeCancelled);
            return ResponseEntity.ok(updatedOffer);

        } else {
            //throw OfferNotFound Exception
            throw new OfferNotFoundException(OFFER_NOT_FOUND);
        }

    }


}
