package bookstore.mapper;

import bookstore.database_entity.BookEntity;
import bookstore.dto.BookDto;

public class BookEntityToDtoMapper {
    public static BookDto map(BookEntity bookEntity) {
        BookDto bookDto = new BookDto();
        bookDto.setBookId(bookEntity.getBookId());
        bookDto.setTitle(bookEntity.getTitle());
        bookDto.setAuthor(bookEntity.getAuthor());
        bookDto.setGenre(bookEntity.getGenre());
        return bookDto;
    }
}
