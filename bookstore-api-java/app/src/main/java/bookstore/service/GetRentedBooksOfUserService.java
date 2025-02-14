package bookstore.service;

import bookstore.database_entity.CustomerEntity;
import bookstore.database_entity.PhysicalBookEntity;
import bookstore.dto.PhysicalBookDto;
import bookstore.exception.UserDoesNotExistExceptionInterface;
import bookstore.mapper.PhysicalBookEntityToDtoMapper;
import bookstore.repository.CustomerRepository;
import bookstore.repository.PhysicalBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static bookstore.util.CustomerUtils.doesUserExist;

@Service
public class GetRentedBooksOfUserService {
    private final PhysicalBookRepository physicalBookRepository;
    private final CustomerRepository customerRepository;

    public GetRentedBooksOfUserService(PhysicalBookRepository physicalBookRepository, CustomerRepository customerRepository) {
        this.physicalBookRepository = physicalBookRepository;
        this.customerRepository = customerRepository;
    }

    public List<PhysicalBookDto> execute(String customerEmail) throws UserDoesNotExistExceptionInterface {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(customerEmail);
        if(!doesUserExist(customerEmail,customerRepository)) {
            throw new UserDoesNotExistExceptionInterface("User does not exist");
        }
        List<PhysicalBookEntity> booksByCustomer = physicalBookRepository.findByCustomerEntity_Email(customerEmail);
        List<PhysicalBookDto> physicalBooksOfUser = booksByCustomer.stream().map(PhysicalBookEntityToDtoMapper::map).toList();
        return physicalBooksOfUser;
    }
}
