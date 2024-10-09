package bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GetAllBooksController {
    @GetMapping("/api/get-all-books")
    public String getAllBooksController() {
        return null;
    }
}
