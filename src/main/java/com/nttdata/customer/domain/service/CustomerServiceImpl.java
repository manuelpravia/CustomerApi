package com.nttdata.customer.domain.service;

import com.nttdata.customer.exception.CustomerNotFoundException;
import com.nttdata.customer.infraestructure.data.document.Customer;
import com.nttdata.customer.infraestructure.data.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Mono<Customer> validateAndGetCustomer(String id) {
        return customerRepository.findById(id).switchIfEmpty(Mono.error(new CustomerNotFoundException(id)));
    }

    @Override
    public Flux<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Void> deleteCustomer(Customer customer) {
        return customerRepository.delete(customer);
    }
}
