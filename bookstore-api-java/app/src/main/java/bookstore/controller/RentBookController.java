package bookstore.controller;

import bookstore.database_entity.PhysicalBookEntity;
import bookstore.dto.BookDto;
import bookstore.dto.PhysicalBookDto;
import bookstore.repository.PhysicalBookRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rent-book")
public class RentBookController {

    private final PhysicalBookRepository physicalBookRepository;

    public RentBookController(PhysicalBookRepository physicalBookRepository) {
        this.physicalBookRepository = physicalBookRepository;
    }

    @PostMapping
    public PhysicalBookDto rentBookController(BookDto bookDto) {
        List<PhysicalBookEntity> physicalBooks = physicalBookRepository.findByBookEntity_BookId(bookDto.getBookId());
        // TODO: get available books
        physicalBooks.stream().filter(physicalBookEntity -> physicalBookEntity.getCustomerEntity() != null).toList();
        return null;
    }
}
