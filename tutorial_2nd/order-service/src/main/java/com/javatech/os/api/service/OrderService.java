package com.javatech.os.api.service;

import com.javatech.os.api.common.Payment;
import com.javatech.os.api.common.TransactionRequest;
import com.javatech.os.api.common.TransactionResponse;
import com.javatech.os.api.entity.Order;
import com.javatech.os.api.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service @RequiredArgsConstructor
public class OrderService {

    @Autowired
    private final OrderRepository repository;
    private final RestTemplate template;

    public TransactionResponse saveOrder(TransactionRequest request) {
        String response = "";
        Order order = request.getOrder();
        repository.save(order);

        Payment payment = request.getPayment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        // rest call
        Payment paymentResponse = template.postForObject("http://PAYMENT-SERVICE/payment/do-payment", payment, Payment.class);

        assert paymentResponse != null;
        response = paymentResponse.getPaymentStatus().equals("success") ?
                "payment processing successful and order placed":
                "there is a failure in payment api, order added to cart";

        return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(), response);
    }
}
