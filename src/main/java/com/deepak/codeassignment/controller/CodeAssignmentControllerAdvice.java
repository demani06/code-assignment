package com.deepak.codeassignment.controller;

/*
 * Default exception handler
 * */

import com.deepak.codeassignment.exception.OfferNotFoundException;
import com.deepak.codeassignment.model.CommonErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Arrays;
import java.util.List;

@ControllerAdvice
@Slf4j
public class CodeAssignmentControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({OfferNotFoundException.class})
    public final ResponseEntity handleOfferNotFoundException(OfferNotFoundException exc) {
        log.info("OfferNotFoundException handle method");

        List<String> errorMessageDetails = Arrays.asList(exc.getLocalizedMessage());

        CommonErrorResponse commonErrorResponse = CommonErrorResponse.builder().errorMessage("Offer Not found").errorMessageDetails(errorMessageDetails).build();

        return new ResponseEntity(commonErrorResponse, HttpStatus.NOT_FOUND);

    }
}
