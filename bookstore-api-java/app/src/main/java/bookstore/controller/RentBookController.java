package bookstore.controller;

import bookstore.dto.BookDto;
import bookstore.dto.PhysicalBookDto;
import bookstore.service.RentBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/bookstore/book/rent-book")
public class RentBookController {

    private final RentBookService rentBookService;

    public RentBookController(RentBookService rentBookService) {
        this.rentBookService = rentBookService;
    }

    @PostMapping
    public ResponseEntity rentBookController(@RequestBody BookDto bookDto, String username) {
        PhysicalBookDto bookRentedDto = null;
        try {
            bookRentedDto = this.rentBookService.execute(bookDto, username);
        }
        catch (RuntimeException e) {
            return new ResponseEntity(e,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(bookRentedDto, HttpStatus.OK);
    }
}
