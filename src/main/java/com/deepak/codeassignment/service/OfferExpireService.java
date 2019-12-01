package com.deepak.codeassignment.service;

import com.deepak.codeassignment.model.Offer;
import com.deepak.codeassignment.model.OfferStatusEnum;
import com.deepak.codeassignment.repository.OfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


/*
 * This service injected as a Spring component and it runs at a frequency defined in the properties file
 * This service reads if there are any expired deals and updates the status to expired
 * */

@Component
@Slf4j
public class OfferExpireService {

    @Autowired
    private OfferRepository offerRepository;

    //This can be configured as cron as well, for simplicity have put as fixedRate
    @Scheduled(fixedRateString = "${fixedRate.in.milliseconds}")
    public void checkAndUpdateOffersIfExpired() {
        log.info("Checking if any offers expired");

        //To see if any deals expired but still showing live, offers with cancelled are not considered because are already considered as expired manually
        List<Offer> expiredOffersList = offerRepository.findAllWithEndDateBefore(LocalDateTime.now(), OfferStatusEnum.LIVE);

        log.info("expiredOffersList = {}", expiredOffersList);

        if (expiredOffersList.size() > 0) {
            log.debug("expired Offers found = {}", expiredOffersList);

            //Iterate the list and update the status to expired
            for (Offer expiredOffer : expiredOffersList) {
                log.info("Updating the status to expired for expired offer id , offer id = {} and offer status = {}", expiredOffer.getOfferDescription(), expiredOffer.getOfferStatus());

                //Update the status to expired
                expiredOffer.setOfferStatus(OfferStatusEnum.EXPIRED);
                //Todo set the modified date
                offerRepository.saveAndFlush(expiredOffer);
            }

        } else {
            log.info("No expired deals found");
        }

    }
}
