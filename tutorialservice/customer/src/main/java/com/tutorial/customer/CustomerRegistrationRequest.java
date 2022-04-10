package com.tutorial.customer;

public record CustomerRegistrationRequest(
        String name,
        String nickname,
        String email) {
}
