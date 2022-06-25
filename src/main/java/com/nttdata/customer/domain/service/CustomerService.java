package com.nttdata.customer.domain.service;

import com.nttdata.customer.infraestructure.data.document.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    Mono<Customer> validateAndGetCustomer(String id);

    Flux<Customer> getCustomers();

    Mono<Customer> saveCustomer(Customer customer);

    Mono<Void> deleteCustomer(Customer customer);
}
