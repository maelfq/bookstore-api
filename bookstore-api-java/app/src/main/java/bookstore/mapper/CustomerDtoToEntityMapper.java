package bookstore.mapper;

import bookstore.database_entity.CustomerEntity;
import bookstore.dto.CustomerDto;

public class CustomerDtoToEntityMapper {
    public static CustomerEntity map(CustomerDto customerDto) {
        if(customerDto != null) {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setEmail(customerDto.getEmail());
            customerEntity.setName(customerDto.getName());
            customerEntity.setPassword(customerDto.getPassword());
            return customerEntity;
        }
        return null;
    }
}
