package com.nttdata.customer.infraestructure.data.repository;

import com.nttdata.customer.infraestructure.data.document.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<Customer,String> {
}
