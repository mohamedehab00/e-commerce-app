package com.ecommerce.app.service;

import com.ecommerce.app.model.Country;
import com.ecommerce.app.model.CountryDto;
import com.ecommerce.app.model.PaymentMethod;

import java.util.List;

public interface LookupsService {
    List<PaymentMethod> getPaymentMethods();
    List<CountryDto> getCountriesAndItsAssociatedCities();
}
