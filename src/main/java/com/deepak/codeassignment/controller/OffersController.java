package com.deepak.codeassignment.controller;

import com.deepak.codeassignment.utils.Endpoints;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * This class would have endpoints w.r.t dealing with offers like creating offer, get an offer, cancel an offer
 * */

@RestController
@Slf4j
public class OffersController {


    @GetMapping(Endpoints.OFFERS_ENDPOINT)
    public void getOffers() {
        //Todo real implementation
        log.debug("Called Get offers end point");
    }


    @PostMapping(Endpoints.OFFERS_ENDPOINT)
    public void createAnOffer() {
        //Todo real implementation
        log.debug("Creating offer ....");

    }

    //Todo
    @PutMapping(Endpoints.OFFERS_ENDPOINT)
    public void cancelOffer() {
        //Todo real implementation
        log.debug("Cancelling offer ....");

    }


}
