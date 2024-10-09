package bookstore.service;

import bookstore.database_entity.CustomerEntity;
import bookstore.database_entity.PhysicalBookEntity;
import bookstore.dto.BookDto;
import bookstore.dto.PhysicalBookDto;
import bookstore.mapper.CustomerEntityToDtoMapper;
import bookstore.mapper.PhysicalBookDtoToEntityMapper;
import bookstore.mapper.PhysicalBookEntityToDtoMapper;
import bookstore.repository.CustomerRepository;
import bookstore.repository.PhysicalBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentBookService {

    private final PhysicalBookRepository physicalBookRepository;
    private final CustomerRepository customerRepository;

    public final static String ERROR_MESSAGE_BOOK_NOT_FOUND = "No available books found.";
    public final static String ERROR_MESSAGE_USER_NOT_FOUND = "The customer provided does not exist.";

    public RentBookService(PhysicalBookRepository physicalBookRepository, CustomerRepository customerRepository) {
        this.physicalBookRepository = physicalBookRepository;
        this.customerRepository = customerRepository;
    }

    public PhysicalBookDto execute(BookDto bookDto, String username) {
        List<PhysicalBookEntity> physicalBooks = physicalBookRepository.findByBookEntity_BookId(bookDto.getBookId());

        List<PhysicalBookDto> physicalBookDtos = physicalBooks.stream().map(PhysicalBookEntityToDtoMapper::map).toList();
        List<PhysicalBookDto> booksAvailable = physicalBookDtos.stream().
                filter(physicalBookDto -> physicalBookDto.getCustomerDto() == null).toList();

        Optional<PhysicalBookDto> optionalBookFound = booksAvailable.stream().findFirst();
        if(optionalBookFound.isEmpty()) {
            throw new RuntimeException(ERROR_MESSAGE_BOOK_NOT_FOUND);
        }
        PhysicalBookDto physicalBookDto = optionalBookFound.get();
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(username);
        if (optionalCustomer.isEmpty()) {
            throw new RuntimeException(ERROR_MESSAGE_USER_NOT_FOUND);
        }
        physicalBookDto.setCustomerDto(CustomerEntityToDtoMapper.map(optionalCustomer.get()));
        PhysicalBookEntity physicalBookToSave = PhysicalBookDtoToEntityMapper.map(physicalBookDto);
        PhysicalBookEntity bookRented = physicalBookRepository.save(physicalBookToSave);
        PhysicalBookDto bookRentedDto = PhysicalBookEntityToDtoMapper.map(bookRented);
        return bookRentedDto;
    }
}
