package bookstore.mapper;

import bookstore.database_entity.BookEntity;
import bookstore.dto.BookDto;

public class BookDtoToEntityMapper {
    public static BookEntity map(BookDto bookDto) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookId(bookDto.getBookId());
        bookEntity.setAuthor(bookDto.getAuthor());
        bookEntity.setTitle(bookDto.getTitle());
        bookEntity.setGenre(bookDto.getGenre());
        return bookEntity;
    }
}
