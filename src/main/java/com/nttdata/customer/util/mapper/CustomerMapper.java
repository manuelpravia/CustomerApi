package com.nttdata.customer.util.mapper;

import com.nttdata.customer.domain.dto.CreateCustomerRequest;
import com.nttdata.customer.domain.dto.CustomerResponse;
import com.nttdata.customer.domain.dto.UpdateCustomerRequest;
import com.nttdata.customer.infraestructure.data.document.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);

    CustomerResponse toCustomerResponse(Customer customer);

    @Mapping(target = "id", ignore = true)
    void updateCustomerFromRequest(UpdateCustomerRequest updateCustomerRequest, @MappingTarget Customer customer);
}
