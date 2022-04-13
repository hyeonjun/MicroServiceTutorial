package com.javetech.ps.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javetech.ps.api.entity.Payment;
import com.javetech.ps.api.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service @RequiredArgsConstructor @Slf4j
public class PaymentService {

    @Autowired
    private final PaymentRepository repository;

    public Payment doPayment(Payment payment) throws JsonProcessingException {
        payment.setPaymentStatus(paymentProcessing());
        payment.setTransactionId(UUID.randomUUID().toString());
        log.info("Payment Service Request: {}", new ObjectMapper().writeValueAsString(payment));
        return repository.save(payment);
    }

    public String paymentProcessing() {
        // api should be 3rd party payment gateway (payal, paytm, ... )
        boolean flag = new Random().nextBoolean();
        log.info("Boolean is {}", flag);
        return flag ? "success" : "fail";
    }

    public Payment findPaymentHistoryByOrderId(Integer orderId) throws JsonProcessingException {
        Payment payment = repository.findByOrderId(orderId);
        log.info("Payment Service findPaymentHistoryByOrderId: {}", new ObjectMapper().writeValueAsString(payment));
        return payment;
    }
}
