package com.javatech.cloud.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/order-fallback")
    public Mono<String> orderServiceFallback() {
        return Mono.just("Order Service is taking too long to respond or is down. Please try again later");
    }

    @RequestMapping("/payment-fallback")
    public Mono<String> paymentServiceFallback() {
        return Mono.just("Payment Service is taking too long to respond or is down. Please try again later");
    }
}
