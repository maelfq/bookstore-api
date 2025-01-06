package bookstore.service;

import bookstore.constant.BookstoreConstants;
import bookstore.dto.BookDto;
import bookstore.mapper.CsvItemToBookDtoMapper;
import bookstore.util.ParseCsvUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoadDatasetService {
    public static boolean execute() {
        var datasetCsv = ClassLoader.getSystemResource(BookstoreConstants.BOOKSTORE_DATASET_CSV);
        List<String> bookLines = ParseCsvUtil.parseFileAsListOfLines(datasetCsv);
        List<BookDto> books = getAllBooksToInsert(bookLines);
        //TODO: call to repository
        return true;
    }

    private static List<BookDto> getAllBooksToInsert(List<String> bookLines) {
        List<BookDto> books = new ArrayList<>();
        String csvHeader = bookLines.get(0);
        List<String> bookLinesWithoutHeader = bookLines.subList(1,bookLines.size());
        for(int index = 0; index < bookLinesWithoutHeader.size(); index++) {
            try {
                books.add(CsvItemToBookDtoMapper.mapCsvStringToBookDto(csvHeader,",",bookLinesWithoutHeader.get(index)));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return books;
    }
}
