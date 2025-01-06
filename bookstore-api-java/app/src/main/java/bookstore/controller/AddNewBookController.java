package bookstore.controller;

import bookstore.dto.BookDto;
import bookstore.service.AddNewBookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/add-new-book")
public class AddNewBookController {

    private final AddNewBookService addNewBookService;

    public AddNewBookController(AddNewBookService addNewBookService) {
        this.addNewBookService = addNewBookService;
    }

    @PostMapping
    public BookDto addNewBookController(BookDto bookDto) {
        BookDto savedDto = addNewBookService.execute(bookDto);
        return savedDto;
    }
}
