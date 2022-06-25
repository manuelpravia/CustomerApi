package com.nttdata.customer.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCustomerRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String phone;
    @NotBlank
    private  String type;
}
