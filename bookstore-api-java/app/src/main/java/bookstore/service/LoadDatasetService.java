package bookstore.service;

import bookstore.constant.BookstoreConstants;
import bookstore.database_entity.BookEntity;
import bookstore.dto.BookDto;
import bookstore.mapper.BookDtoToEntityMapper;
import bookstore.mapper.BookEntityToDtoMapper;
import bookstore.mapper.CsvItemToBookDtoMapper;
import bookstore.repository.BookRepository;
import bookstore.util.ParseCsvUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoadDatasetService {

    private final BookRepository bookRepository;

    private final GeneratePhysicalBooksService generatePhysicalBooksService;

    public LoadDatasetService(BookRepository bookRepository, GeneratePhysicalBooksService generatePhysicalBooksService) {
        this.bookRepository = bookRepository;
        this.generatePhysicalBooksService = generatePhysicalBooksService;
    }

    public boolean execute() {
        var datasetCsv = ClassLoader.getSystemResource(BookstoreConstants.BOOKSTORE_DATASET_CSV);
        List<String> bookLines = ParseCsvUtil.parseFileAsListOfLines(datasetCsv);
        List<BookDto> books = getAllBooksToInsert(bookLines);
        List<BookEntity> bookEntities = books.stream().map(BookDtoToEntityMapper::map).toList();
        List<BookEntity> savedBookEntities = bookRepository.saveAll(bookEntities);
        List<BookDto> savedBookDtos = savedBookEntities.stream().map(BookEntityToDtoMapper::map).toList();

        //TODO: remove service call and extract its call in a controller
        generatePhysicalBooksService.execute(savedBookDtos);
        return true;
    }

    private static List<BookDto> getAllBooksToInsert(List<String> bookLines) {
        List<BookDto> books = new ArrayList<>();
        String csvHeader = bookLines.get(0);
        List<String> bookLinesWithoutHeader = bookLines.subList(1,bookLines.size());
        for(int index = 0; index < bookLinesWithoutHeader.size(); index++) {
            try {
                books.add(CsvItemToBookDtoMapper.mapCsvStringToBookDto(csvHeader, BookstoreConstants.BOOKSTORE_DATASET_SEPARAOR, bookLinesWithoutHeader.get(index)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return books;
    }
}
