package bookstore.controller;

import bookstore.dto.CustomerDto;
import bookstore.service.CreateCustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/create-customer")
public class CreateCustomerController {

    private final CreateCustomerService createCustomerService;

    public CreateCustomerController(CreateCustomerService createCustomerService) {
        this.createCustomerService = createCustomerService;
    }

    @PostMapping
    public CustomerDto createCustomerController(CustomerDto customerDto) {
        return createCustomerService.execute(customerDto);
    }
}
