package bookstore.controller;

import bookstore.dto.PhysicalBookDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rent-book")
public class RentBookController {

    public PhysicalBookDto rentBookController(PhysicalBookDto physicalBookDto) {
        return null;
    }
}
