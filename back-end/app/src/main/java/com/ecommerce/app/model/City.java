package com.ecommerce.app.model;

import jakarta.persistence.*;
import lombok.*;

@ToString(exclude = {"country"})
@Data
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
