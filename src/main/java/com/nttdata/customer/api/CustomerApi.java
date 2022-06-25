package com.nttdata.customer.api;

import com.nttdata.customer.domain.dto.CreateCustomerRequest;
import com.nttdata.customer.domain.dto.CustomerResponse;
import com.nttdata.customer.domain.dto.UpdateCustomerRequest;
import com.nttdata.customer.domain.service.CustomerService;
import com.nttdata.customer.infraestructure.data.document.Customer;
import com.nttdata.customer.util.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/customers")
public class CustomerApi {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping(produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<CustomerResponse> getCustomers(){
        return customerService.getCustomers().map(customerMapper::toCustomerResponse);
    }

    @GetMapping("/{id}")
    public Mono<CustomerResponse> getCustomer(@PathVariable String id){
        return customerService.validateAndGetCustomer(id).map(customerMapper::toCustomerResponse);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Mono<CustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        Customer customer = customerMapper.toCustomer(createCustomerRequest);
        return customerService.saveCustomer(customer).map(customerMapper::toCustomerResponse);
    }

    @PatchMapping("/{id}")
    public Mono<CustomerResponse> updateCustomer(@PathVariable String id,
                                               @RequestBody UpdateCustomerRequest updateCustomerRequest) {
        return customerService.validateAndGetCustomer(id)
                .doOnSuccess(customer -> {
                    customerMapper.updateCustomerFromRequest(updateCustomerRequest, customer);
                    customerService.saveCustomer(customer).subscribe();
                })
                .map(customerMapper::toCustomerResponse);
    }

    @DeleteMapping("/{id}")
    public Mono<CustomerResponse> deleteCustomer(@PathVariable String id) {
        return customerService.validateAndGetCustomer(id)
                .doOnSuccess(customer -> customerService.deleteCustomer(customer).subscribe())
                .map(customerMapper::toCustomerResponse);
    }
}
