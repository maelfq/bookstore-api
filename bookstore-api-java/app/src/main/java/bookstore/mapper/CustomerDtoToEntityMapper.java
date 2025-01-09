package bookstore.mapper;

import bookstore.database_entity.CustomerEntity;
import bookstore.dto.CustomerDto;

public class CustomerDtoToEntityMapper {
    public static CustomerEntity map(CustomerDto customerDto) {
        if(customerDto != null) {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setCustomerId(customerEntity.getCustomerId());
            customerEntity.setName(customerEntity.getName());
            return customerEntity;
        }
        return null;
    }
}
