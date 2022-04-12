package com.javetech.ps.api.service;

import com.javetech.ps.api.entity.Payment;
import com.javetech.ps.api.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class PaymentService {

    @Autowired
    private final PaymentRepository repository;

    public Payment doPayment(Payment payment) {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        return repository.save(payment);
    }

    public String paymentProcessing() {
        // api should be 3rd party payment gateway (payal, paytm, ... )
        return new Random().nextBoolean() ? "success" : "fail";
    }
}
