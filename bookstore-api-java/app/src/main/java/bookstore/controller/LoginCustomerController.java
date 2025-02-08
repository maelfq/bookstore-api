package bookstore.controller;

import bookstore.dto.CustomerDto;
import bookstore.dto.LoginCustomerResponseDto;
import bookstore.exception.AbstractExceptionWithHttpErrorStatus;
import bookstore.service.HandleLoginRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/bookstore/customer/login")
public class LoginCustomerController {

    private final HandleLoginRequestService handleLoginRequestService;

    public LoginCustomerController(HandleLoginRequestService handleLoginRequestService) {
        this.handleLoginRequestService = handleLoginRequestService;
    }

    @GetMapping
    public ResponseEntity loginCustomerController(CustomerDto customerDto) {
        LoginCustomerResponseDto loginCustomerResponseDto;

        try {
            loginCustomerResponseDto = handleLoginRequestService.execute(customerDto);
        } catch (AbstractExceptionWithHttpErrorStatus e) {
            return new ResponseEntity<String>(e.getMessage(), e.getHttpErrorStatus());
        }
        return new ResponseEntity<LoginCustomerResponseDto>(loginCustomerResponseDto, HttpStatus.OK);
    }
}
