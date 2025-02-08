package bookstore.service;

import bookstore.database_entity.CustomerEntity;
import bookstore.dto.CustomerDto;
import bookstore.dto.LoginCustomerResponseDto;
import bookstore.exception.AbstractExceptionWithHttpErrorStatus;
import bookstore.exception.IncorrectPasswordExceptionInterface;
import bookstore.exception.UserDoesNotExistExceptionInterface;
import bookstore.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static bookstore.util.CustomerUtils.doesUserExist;
import static bookstore.util.CustomerUtils.isPasswordCorrect;

@Service
public class HandleLoginRequestService {

    private final CustomerRepository customerRepository;

    public HandleLoginRequestService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public LoginCustomerResponseDto execute(CustomerDto customerDto) throws AbstractExceptionWithHttpErrorStatus {
        return getLoginResponse(customerDto);
    }

    public LoginCustomerResponseDto getLoginResponse(CustomerDto customerDto) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerDto.getEmail());

        if(!doesUserExist(customerDto, customerOptional)) {
            throw new UserDoesNotExistExceptionInterface("User does not exist.");
        }

        if(!isPasswordCorrect(customerDto, customerOptional)) {
            throw new IncorrectPasswordExceptionInterface("Incorrect password.");
        }

        CustomerEntity customer = customerOptional.get();

        LoginCustomerResponseDto response = new LoginCustomerResponseDto();
        response.setName(customer.getName());
        response.setEmail(customer.getEmail());
        return response;
    }
}
