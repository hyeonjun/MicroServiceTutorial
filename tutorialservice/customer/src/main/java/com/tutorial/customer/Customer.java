package com.tutorial.customer;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class Customer {
    private Integer id;
    private String name;
    private String nickname;
    private String email;
}
