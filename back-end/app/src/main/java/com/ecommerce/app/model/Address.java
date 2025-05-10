package com.ecommerce.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "address")
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String zipCode;
}
