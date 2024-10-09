package bookstore.controller;

import bookstore.dto.PhysicalBookDto;
import bookstore.exception.UserDoesNotExistExceptionInterface;
import bookstore.service.GetRentedBooksOfUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/bookstore/customer/books")
public class GetRentedBooksOfUserController {

    private final GetRentedBooksOfUserService getRentedBooksOfUserService;

    public GetRentedBooksOfUserController(GetRentedBooksOfUserService getRentedBooksOfUserService) {
        this.getRentedBooksOfUserService = getRentedBooksOfUserService;
    }

    @GetMapping
    public ResponseEntity getRentedBooksOfUserController(String customerEmail) {
        List<PhysicalBookDto> books = new ArrayList<>();
        try {
            books = getRentedBooksOfUserService.execute(customerEmail);
        } catch (UserDoesNotExistExceptionInterface e) {
            return new ResponseEntity(e, e.getHttpErrorStatus());
        }

        return new ResponseEntity(books, HttpStatus.OK);
    }
}
