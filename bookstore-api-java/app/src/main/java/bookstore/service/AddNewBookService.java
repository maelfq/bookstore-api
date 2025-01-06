package bookstore.service;

import bookstore.database_entity.BookEntity;
import bookstore.dto.BookDto;
import bookstore.mapper.BookDtoToEntityMapper;
import bookstore.mapper.BookEntityToDtoMapper;
import bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class AddNewBookService {

    private final BookRepository bookRepository;

    public AddNewBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto execute(BookDto bookDto) {
        BookEntity bookToSave = BookDtoToEntityMapper.map(bookDto);
        BookEntity saved = bookRepository.save(bookToSave);
        BookDto savedDto = BookEntityToDtoMapper.map(saved);
        return savedDto;
    }
}
