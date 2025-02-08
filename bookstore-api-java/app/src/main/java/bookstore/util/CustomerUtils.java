package bookstore.util;

import bookstore.database_entity.CustomerEntity;
import bookstore.dto.CustomerDto;

import java.util.Optional;

public class CustomerUtils {
    public static Boolean doesUserExist(CustomerDto customerDto, Optional<CustomerEntity> customerOptional) {
        return customerOptional.isPresent();
    }

    public static Boolean isPasswordCorrect(CustomerDto customerDto, Optional<CustomerEntity> customerOptional) {
        CustomerEntity customer = null;
        if(customerOptional.isPresent()) {
            customer = customerOptional.get();
        }
        if(customer != null && customer.getPassword().equals(customerDto.getPassword())) {
            return true;
        }
        return false;
    }
}
