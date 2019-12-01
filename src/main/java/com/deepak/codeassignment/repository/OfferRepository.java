package com.deepak.codeassignment.repository;

import com.deepak.codeassignment.model.Offer;
import com.deepak.codeassignment.model.OfferStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    //This method is to retrieve offers which are expired
    @Query("select o from Offer o where o.endDateTime <= :currentDateTime and o.offerStatus= :offerStatus")
    List<Offer> findAllWithEndDateBefore(@Param("currentDateTime") LocalDateTime currentDateTime, @Param("offerStatus") OfferStatusEnum offerStatus);
}
