package com.nttdata.customer.domain.dto;

import lombok.Data;

@Data
public class UpdateCustomerRequest {

    private String name;
    private String address;
    private String phone;
    private  String type;
}
