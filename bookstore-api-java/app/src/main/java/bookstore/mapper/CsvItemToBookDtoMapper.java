package bookstore.mapper;

import bookstore.dto.BookDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class CsvItemToBookDtoMapper {
    private static String BOOK_TITLE_COLUMN_LABEL = "Book";
    private static String BOOK_AUTHOR_COLUMN_LABEL = "Author";
    private static String BOOK_GENRE_COLUMN_LABEL = "Genre";

    public static BookDto mapCsvStringToBookDto(String csvHeader, String csvSeparator, String input) throws Exception {
        String[] columns = csvHeader.split(csvSeparator);

        String regex = csvSeparator + "(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        String[] values = input.split(regex);
        ArrayList<String> valuesArray = new ArrayList<>(Arrays.asList(values));

        BookDto bookDto = new BookDto();

        //FIXME: genre is often the missing column
        if(columns.length != valuesArray.size()) {
            // condition met when the genre is missing from dataset
            if(valuesArray.size() == (columns.length - 1)) {
                valuesArray.add("Unknown genre");
            }
            else {
                throw new RuntimeException("Invalid input file, there are not as many values as columns declared.");
            }
        }

        setBookTitleProperty(columns, valuesArray, bookDto);
        setBookAuthorProperty(columns, valuesArray, bookDto);
        setBookGenreProperty(columns, valuesArray, bookDto);

        return bookDto;
    }

    private static String setBookTitleProperty(String[] columns, ArrayList<String> values, BookDto bookDto) {
        int columnIndex =  getColumnPosition(BOOK_TITLE_COLUMN_LABEL, columns);
        if(columnIndex != -1) {
            bookDto.setTitle(values.get(columnIndex));
        }
        return bookDto.getTitle();
    }

    private static String setBookAuthorProperty(String[] columns, ArrayList<String> values, BookDto bookDto) {
        int columnIndex =  getColumnPosition(BOOK_AUTHOR_COLUMN_LABEL, columns);
        if(columnIndex != -1) {
            bookDto.setAuthor(values.get(columnIndex));
        }
        return bookDto.getAuthor();
    }

    private static String setBookGenreProperty(String[] columns, ArrayList<String> values, BookDto bookDto) {
        int columnIndex =  getColumnPosition(BOOK_GENRE_COLUMN_LABEL, columns);
        if(columnIndex != -1) {
            bookDto.setGenre(values.get(columnIndex));
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
