package bookstore.controller;

import bookstore.dto.BookDto;
import bookstore.service.GetFeaturedBooksService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/featured-books")
public class GetFeaturedBooksController {

    private final GetFeaturedBooksService getFeaturedBooksService;

    public GetFeaturedBooksController(GetFeaturedBooksService getFeaturedBooksService) {
        this.getFeaturedBooksService = getFeaturedBooksService;
    }

    @CrossOrigin
    @GetMapping
    public List<BookDto> getFeaturedBooksController() {
        return getFeaturedBooksService.execute();
    }
}
