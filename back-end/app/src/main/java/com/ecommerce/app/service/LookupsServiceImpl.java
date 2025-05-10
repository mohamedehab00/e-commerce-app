package com.ecommerce.app.service;

import com.ecommerce.app.model.Country;
import com.ecommerce.app.model.CountryDto;
import com.ecommerce.app.model.PaymentMethod;
import com.ecommerce.app.repository.CountryRepository;
import com.ecommerce.app.repository.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LookupsServiceImpl implements LookupsService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final CountryRepository countryRepository;
    private final MappingUtilService mappingUtilService;

    @Override
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public List<CountryDto> getCountriesAndItsAssociatedCities() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream().map(mappingUtilService::mapCountryToCountryDto).toList();
    }
}
