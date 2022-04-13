package com.javetech.ps.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javetech.ps.api.entity.Payment;
import com.javetech.ps.api.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private final PaymentService service;

    @PostMapping("/do-payment")
    public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {
        return service.doPayment(payment);
    }

    @GetMapping("/{orderId}")
    public Payment findPaymentHistoryByOrderId(@PathVariable Integer orderId) throws JsonProcessingException {
        return service.findPaymentHistoryByOrderId(orderId);
    }
}
