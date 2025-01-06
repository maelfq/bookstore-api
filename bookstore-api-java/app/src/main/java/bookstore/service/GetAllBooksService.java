package bookstore.service;

import bookstore.database_entity.BookEntity;
import bookstore.dto.BookDto;
import bookstore.mapper.BookEntityToDtoMapper;
import bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllBooksService {

    private final BookRepository bookRepository;

    public GetAllBooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> execute() {
        List<BookEntity> allBookEntities = bookRepository.findAll();
        List<BookDto> bookDtos = allBookEntities.stream().map(BookEntityToDtoMapper::map).toList();
        return bookDtos;
    }
}
