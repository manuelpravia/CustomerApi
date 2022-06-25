package com.nttdata.customer.domain.dto;

import lombok.Value;

@Value
public class CustomerResponse {

    private String id;
    private String name;
    private String address;
    private String phone;
    private  String type;
}
