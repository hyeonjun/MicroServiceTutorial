package com.javetech.ps.api.controller;

import com.javetech.ps.api.entity.Payment;
import com.javetech.ps.api.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    @Autowired
    private final PaymentService service;

    @PostMapping("/do-payment")
    public Payment doPayment(@RequestBody Payment payment) {
        return service.doPayment(payment);
    }

}