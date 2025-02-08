package bookstore.mapper;

import bookstore.database_entity.CustomerEntity;
import bookstore.dto.CustomerDto;

public class CustomerEntityToDtoMapper {
    public static CustomerDto map(CustomerEntity customerEntity) {
        if(customerEntity == null) {
            return null;
        }
        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail(customerEntity.getEmail());
        customerDto.setName(customerEntity.getName());
        customerDto.setPassword(customerEntity.getPassword());
        return customerDto;
    }
}
