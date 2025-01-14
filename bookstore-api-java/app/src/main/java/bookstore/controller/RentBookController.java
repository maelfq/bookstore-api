package bookstore.controller;

import bookstore.database_entity.CustomerEntity;
import bookstore.database_entity.PhysicalBookEntity;
import bookstore.dto.BookDto;
import bookstore.dto.PhysicalBookDto;
import bookstore.mapper.CustomerEntityToDtoMapper;
import bookstore.mapper.PhysicalBookDtoToEntityMapper;
import bookstore.mapper.PhysicalBookEntityToDtoMapper;
import bookstore.repository.CustomerRepository;
import bookstore.repository.PhysicalBookRepository;
import bookstore.service.RentBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static bookstore.service.RentBookService.ERROR_MESSAGE_USER_NOT_FOUND;

@RestController
@RequestMapping("/api/rent-book")
public class RentBookController {

    private final RentBookService rentBookService;

    public RentBookController(RentBookService rentBookService) {
        this.rentBookService = rentBookService;
    }

    @PostMapping
    public ResponseEntity rentBookController(@RequestBody BookDto bookDto, Long customerId) {
        PhysicalBookDto bookRentedDto = null;
        try {
            bookRentedDto = this.rentBookService.execute(bookDto, customerId);
        }
        catch (RuntimeException e) {

            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookRentedDto, HttpStatus.OK);
    }
}
