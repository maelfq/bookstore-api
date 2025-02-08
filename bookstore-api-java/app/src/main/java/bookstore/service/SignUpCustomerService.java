package bookstore.service;

import bookstore.database_entity.CustomerEntity;
import bookstore.dto.CustomerDto;
import bookstore.exception.InvalidUserRegistrationException;
import bookstore.exception.UserAlreadyExistsException;
import bookstore.mapper.CustomerDtoToEntityMapper;
import bookstore.mapper.CustomerEntityToDtoMapper;
import bookstore.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static bookstore.util.CustomerUtils.doesUserExist;

@Service
public class SignUpCustomerService {

    private final CustomerRepository customerRepository;

    public SignUpCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto execute(CustomerDto customerDto) {

        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerDto.getEmail());
        if(doesUserExist(customerDto,customerOptional)) {
            throw new UserAlreadyExistsException("User already exists.");
        }
        if(!isCustomerRegistrationValid(customerDto)) {
            throw new InvalidUserRegistrationException("User email is not valid");
        }

        CustomerEntity customerEntityToSave = CustomerDtoToEntityMapper.map(customerDto);
        CustomerEntity savedCustomer = customerRepository.save(customerEntityToSave);
        return CustomerEntityToDtoMapper.map(savedCustomer);
    }

    private boolean isCustomerRegistrationValid(CustomerDto customerDto) {

        String email = customerDto.getEmail();
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
