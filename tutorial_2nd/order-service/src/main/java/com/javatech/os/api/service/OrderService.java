package com.javatech.os.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatech.os.api.common.Payment;
import com.javatech.os.api.common.TransactionRequest;
import com.javatech.os.api.common.TransactionResponse;
import com.javatech.os.api.entity.Order;
import com.javatech.os.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service @RequiredArgsConstructor
@RefreshScope @Slf4j
public class OrderService {

    @Autowired
    private final OrderRepository repository;

    @Autowired @Lazy
    private final RestTemplate template;

    @Value("${microservice.payment-service.endpoints.endpoint.uri}")
    private String ENDPOINT_URL;

    public TransactionResponse saveOrder(TransactionRequest request) throws JsonProcessingException {
        String response = "";
        Order order = request.getOrder();
        repository.save(order);

        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        // rest call
        log.info("Order Service Request: {}", new ObjectMapper().writeValueAsString(request));
        Payment paymentResponse = template.postForObject(ENDPOINT_URL, payment, Payment.class);
        log.info("Payment Service Response from Order Service Rest Call: {}", new ObjectMapper().writeValueAsString(paymentResponse));

        assert paymentResponse != null;
        response = paymentResponse.getPaymentStatus().equals("success") ?
                "payment processing successful and order placed":
                "there is a failure in payment api, order added to cart";

        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
    }
}
