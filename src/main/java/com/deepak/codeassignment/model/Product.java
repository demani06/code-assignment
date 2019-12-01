package com.deepak.codeassignment.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    private Long productId;
    //TODO push this to an enum because this will be a standard set of reference data
    private String productName;
    private String section;

}
