package bookstore.service;

import org.springframework.stereotype.Service;

import bookstore.dto.PhysicalBookDto;
import bookstore.exception.UserDoesNotExistExceptionInterface;
import bookstore.mapper.CustomerEntityToDtoMapper;
import bookstore.mapper.PhysicalBookDtoToEntityMapper;
import bookstore.mapper.PhysicalBookEntityToDtoMapper;
import bookstore.database_entity.CustomerEntity;
import bookstore.database_entity.PhysicalBookEntity;
import bookstore.dto.CustomerDto;
import bookstore.repository.PhysicalBookRepository;
import bookstore.util.CustomerUtils;
import bookstore.repository.CustomerRepository;

@Service
public class UpdatePhysicalBookService {

    private final PhysicalBookRepository physicalBookRepository;
    private final CustomerRepository customerRepository;

    public UpdatePhysicalBookService(PhysicalBookRepository physicalBookRepository, CustomerRepository customerRepository) {
        this.physicalBookRepository = physicalBookRepository;
        this.customerRepository = customerRepository;
    }
    
    public PhysicalBookDto execute(PhysicalBookDto physicalBookDto) {
        CustomerDto customerDto = physicalBookDto.getCustomerDto();
        if(customerDto != null && customerDto.getEmail() != null && CustomerUtils.doesUserExist(customerDto.getEmail(), customerRepository)) {
            CustomerEntity customer = this.customerRepository.findById(customerDto.getEmail()).get();
            CustomerDto customerFoundDto = CustomerEntityToDtoMapper.map(customer);
            physicalBookDto.setCustomerDto(customerFoundDto);
        }
        else if(customerDto != null && customerDto.getEmail() != null && !CustomerUtils.doesUserExist(customerDto.getEmail(), customerRepository)) {
            throw new UserDoesNotExistExceptionInterface("User does not exist");
        }
        PhysicalBookEntity physicalBookToUpdate = PhysicalBookDtoToEntityMapper.map(physicalBookDto); 
        PhysicalBookEntity physicalBookUpdated = this.physicalBookRepository.save(physicalBookToUpdate);
        PhysicalBookDto physicalBookUpdatedDto = PhysicalBookEntityToDtoMapper.map(physicalBookUpdated);
        return physicalBookUpdatedDto;
    }

}
