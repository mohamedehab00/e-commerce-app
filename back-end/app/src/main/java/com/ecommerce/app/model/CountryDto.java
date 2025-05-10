package com.ecommerce.app.model;

import com.ecommerce.app.dto.CityDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CountryDto {
    private Long id;
    private String name;
    private List<CityDto> cities;
}
