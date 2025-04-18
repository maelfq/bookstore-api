package bookstore.controller;

import bookstore.dto.BookDto;
import bookstore.service.GetAllBooksService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookstore/book/get-all-books")
public class GetAllBooksController {

    private final GetAllBooksService getAllBooksService;

    public GetAllBooksController(GetAllBooksService getAllBooksService) {
        this.getAllBooksService = getAllBooksService;
    }

    @CrossOrigin
    @GetMapping
    public List<BookDto> getAllBooksController() {
        return getAllBooksService.execute();
    }
}
