package bookstore.controller;

import bookstore.dto.CustomerDto;
import bookstore.exception.AbstractExceptionWithHttpErrorStatus;
import bookstore.service.SignUpCustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookstore/customer/sign-up")
public class SignUpCustomerController {

    private final SignUpCustomerService signUpCustomerService;

    public SignUpCustomerController(SignUpCustomerService signUpCustomerService) {
        this.signUpCustomerService = signUpCustomerService;
    }

    @PostMapping
    public ResponseEntity createCustomerController(CustomerDto customerDto) {
        CustomerDto customerDtoCreated = null;
        try {
            customerDtoCreated = signUpCustomerService.execute(customerDto);
        }
        catch (AbstractExceptionWithHttpErrorStatus e) {
            return new ResponseEntity(e.getMessage(), e.getHttpErrorStatus());
        }
        return new ResponseEntity(customerDtoCreated, HttpStatus.OK);
    }
}
