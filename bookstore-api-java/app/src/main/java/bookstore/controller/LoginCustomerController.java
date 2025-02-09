package bookstore.controller;

import bookstore.dto.CustomerDto;
import bookstore.dto.LoginCustomerResponseDto;
import bookstore.exception.AbstractExceptionWithHttpErrorStatus;
import bookstore.service.HandleLoginRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/bookstore/customer/login")
public class LoginCustomerController {

    private final HandleLoginRequestService handleLoginRequestService;

    public LoginCustomerController(HandleLoginRequestService handleLoginRequestService) {
        this.handleLoginRequestService = handleLoginRequestService;
    }


    @PutMapping
    public ResponseEntity loginCustomerController(@RequestBody CustomerDto customerDto) {
        LoginCustomerResponseDto loginCustomerResponseDto;

        try {
            loginCustomerResponseDto = handleLoginRequestService.execute(customerDto);
        } catch (AbstractExceptionWithHttpErrorStatus e) {
            return new ResponseEntity(e, e.getHttpErrorStatus());
        }
        return new ResponseEntity(loginCustomerResponseDto, HttpStatus.OK);
    }
}
