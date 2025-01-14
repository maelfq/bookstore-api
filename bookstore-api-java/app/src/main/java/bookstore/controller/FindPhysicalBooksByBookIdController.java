package bookstore.controller;

import bookstore.dto.PhysicalBookDto;
import bookstore.service.FindPhysicalBooksByBookIdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/get-books-by-id")
public class FindPhysicalBooksByBookIdController {

    private final FindPhysicalBooksByBookIdService findPhysicalBooksByBookIdService;

    public FindPhysicalBooksByBookIdController(FindPhysicalBooksByBookIdService findPhysicalBooksByBookIdService) {
        this.findPhysicalBooksByBookIdService = findPhysicalBooksByBookIdService;
    }

    @GetMapping
    public ResponseEntity<List<PhysicalBookDto>> findPhysicalBooksByBookId(Long bookId) {
        List<PhysicalBookDto> physicalBookDtos = this.findPhysicalBooksByBookIdService.execute(bookId);
        return new ResponseEntity<>(physicalBookDtos, HttpStatus.OK);
    }
}
