package com.javetech.ps.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENT_TB")
@Data
@AllArgsConstructor @NoArgsConstructor
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;
    private String paymentStatus;
    private String transactionId;
    private Integer OrderId;
    private Double amount;
}
