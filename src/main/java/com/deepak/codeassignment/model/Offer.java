package com.deepak.codeassignment.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;
    @NotEmpty(message = "Offer Description cannot be empty")
    private String offerDescription;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    @Enumerated(EnumType.STRING)
    private OfferStatusEnum offerStatus;

    private CurrencyEnum currency;

    @OneToOne
    private Product product;

    @Override
    public String toString() {
        return "Offer{" +
                "offerDescription='" + offerDescription + '\'' +
                " on product =" + product.getProductName() +
                "and its expiring on " + endDateTime +
                '}';
    }
}
