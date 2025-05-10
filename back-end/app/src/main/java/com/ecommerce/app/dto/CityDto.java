package com.ecommerce.app.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CityDto {
    private Long id;
    private String name;
}
