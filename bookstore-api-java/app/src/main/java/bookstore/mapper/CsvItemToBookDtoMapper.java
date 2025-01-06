package bookstore.mapper;

import bookstore.dto.BookDto;
import org.springframework.stereotype.Component;

@Component
public class CsvItemToBookDtoMapper {
    private static String BOOK_TITLE_COLUMN_LABEL = "Book";
    private static String BOOK_AUTHOR_COLUMN_LABEL = "Author";
    private static String BOOK_GENRE_COLUMN_LABEL = "Genre";

    public static BookDto mapCsvStringToBookDto(String csvHeader, String csvSeparator, String input) throws Exception {
        String[] columns = csvHeader.split(csvSeparator);
        String[] values = input.split(csvSeparator);

        if(columns.length != values.length) {
            throw new Exception("Invalid input file, there are not as many values as columns declared.");
        }

        BookDto bookDto = new BookDto();
        setBookTitleProperty(columns, values, bookDto);
        setBookAuthorProperty(columns, values, bookDto);
        setBookGenreProperty(columns, values, bookDto);

        return bookDto;
    }

    private static String setBookTitleProperty(String[] columns, String[] values, BookDto bookDto) {
        int columnIndex =  getColumnPosition(BOOK_TITLE_COLUMN_LABEL, columns);
        if(columnIndex != -1) {
            bookDto.setTitle(values[columnIndex]);
        }
        return bookDto.getTitle();
    }

    private static String setBookAuthorProperty(String[] columns, String[] values, BookDto bookDto) {
        int columnIndex =  getColumnPosition(BOOK_AUTHOR_COLUMN_LABEL, columns);
        if(columnIndex != -1) {
            bookDto.setAuthor(values[columnIndex]);
        }
        return bookDto.getAuthor();
    }

    private static String setBookGenreProperty(String[] columns, String[] values, BookDto bookDto) {
        int columnIndex =  getColumnPosition(BOOK_GENRE_COLUMN_LABEL, columns);
        if(columnIndex != -1) {
            bookDto.setGenre(values[columnIndex]);
        }
        return bookDto.getGenre();
    }

    private static int getColumnPosition(String columnName, String[] columns) {
        for(int index = 0; index < columns.length; index++) {
            if(columns[index].contains(columnName)) {
                return index;
            }
        }
        return -1;
    }
}
