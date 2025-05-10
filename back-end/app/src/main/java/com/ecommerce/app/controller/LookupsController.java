package com.ecommerce.app.controller;

import com.ecommerce.app.model.Country;
import com.ecommerce.app.model.CountryDto;
import com.ecommerce.app.model.PaymentMethod;
import com.ecommerce.app.service.LookupsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/lookup")
public class LookupsController {
    private final LookupsService lookupsService;

    @GetMapping(value = "/payment-methods")
    public ResponseEntity<List<PaymentMethod>> paymentMethods() {
        return new ResponseEntity<>(lookupsService.getPaymentMethods(), HttpStatus.OK);
    }

    @GetMapping(value = "/countries")
    public ResponseEntity<List<CountryDto>> countriesList() {
        return new ResponseEntity<>(lookupsService.getCountriesAndItsAssociatedCities(), HttpStatus.OK);
    }
}
