package bookstore.mapper;

import bookstore.database_entity.CustomerEntity;
import bookstore.dto.CustomerDto;

public class CustomerEntityToDtoMapper {
    public static CustomerDto map(CustomerEntity customerEntity) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(customerDto.getCustomerId());
        customerDto.setName(customerDto.getName());
        return customerDto;
    }
}
