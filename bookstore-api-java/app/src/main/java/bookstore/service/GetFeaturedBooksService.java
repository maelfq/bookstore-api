package bookstore.service;

import bookstore.database_entity.BookEntity;
import bookstore.dto.BookDto;
import bookstore.mapper.BookEntityToDtoMapper;
import bookstore.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class GetFeaturedBooksService {

    private final BookRepository bookRepository;

    public GetFeaturedBooksService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> execute() {
        long bookCount = bookRepository.count();
        if( bookCount < 10) {
            return null;
        }
        Random random = new Random();
        ArrayList<BookDto> books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int pageNumber = random.nextInt((int) bookCount);
            Page<BookEntity> bookPage = bookRepository.findAll(PageRequest.of(pageNumber,1));
            if(!bookPage.hasContent()) {
                return null;
            }
            List<BookDto> randomBookList = bookPage.stream().map(BookEntityToDtoMapper::map).toList();
            if(!randomBookList.isEmpty()) {
                BookDto randomBook = randomBookList.get(0);
                if (!isBookAlreadyInList(randomBook,books)) {
                    books.add(randomBook);
                }
            }
        }
        return books;
    }

    private boolean isBookAlreadyInList(BookDto book, List<BookDto> list) {
        // copy list so that we don't change original by using Stream API
        List<BookDto> listTmp = new ArrayList<>(list);
        List<Long> bookMatches = listTmp.stream().map(bookDto -> bookDto.getBookId()).filter(aLong -> Objects.equals(aLong, book.getBookId())).toList();
        return bookMatches.size() > 1;
    }
}
