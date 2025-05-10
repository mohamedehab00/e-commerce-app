package com.ecommerce.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "payment_method")
@Entity
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "method_name")
    private String name;
}
