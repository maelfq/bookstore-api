package bookstore.mapper;

import bookstore.dto.BookDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CsvItemToBookDtoMapperTest {
    @Test
    void mapCsvStringToBookDtoTest() throws Exception {
        String lineToMap = "A Tale of Two Cities,Charles Dickens,English,1859,200,Historical fiction";
        String header = "Book,Author(s),Original language,First published,Approximate sales in millions,Genre";
        String separator = ",";
        BookDto actual = CsvItemToBookDtoMapper.mapCsvStringToBookDto(header, separator, lineToMap);

        BookDto expected = new BookDto();
        expected.setAuthor("Charles Dickens");
        expected.setTitle("A Tale of Two Cities");
        expected.setGenre("Historical fiction");

        assertEquals(actual.getAuthor(),expected.getAuthor());
        assertEquals(actual.getTitle(),expected.getTitle());
        assertEquals(actual.getGenre(),expected.getGenre());
    }

}