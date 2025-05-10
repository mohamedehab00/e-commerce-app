package com.ecommerce.app.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressDetails {
    private String street;
    private Long city;
    private Long country;
    private String zip;
}
