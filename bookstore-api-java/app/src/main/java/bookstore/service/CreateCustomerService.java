package bookstore.service;

import bookstore.database_entity.CustomerEntity;
import bookstore.dto.CustomerDto;
import bookstore.mapper.CustomerDtoToEntityMapper;
import bookstore.mapper.CustomerEntityToDtoMapper;
import bookstore.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateCustomerService {

    private final CustomerRepository customerRepository;

    public CreateCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto execute(CustomerDto customerDto) {
        CustomerEntity customerEntityToSave = CustomerDtoToEntityMapper.map(customerDto);
        CustomerEntity savedCustomer = customerRepository.save(customerEntityToSave);
        return CustomerEntityToDtoMapper.map(savedCustomer);
    }
}
