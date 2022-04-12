package com.javatech.os.api.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Payment {

    private Integer paymentId;
    private String paymentStatus;
    private String transactionId;
    private Integer OrderId;
    private Double amount;
}
