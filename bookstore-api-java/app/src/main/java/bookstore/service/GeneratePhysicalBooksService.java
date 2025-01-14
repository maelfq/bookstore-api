package bookstore.service;

import bookstore.database_entity.PhysicalBookEntity;
import bookstore.dto.BookDto;
import bookstore.dto.PhysicalBookDto;
import bookstore.dto.PhysicalBookStateEnum;
import bookstore.mapper.PhysicalBookDtoToEntityMapper;
import bookstore.mapper.PhysicalBookEntityToDtoMapper;
import bookstore.repository.PhysicalBookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class GeneratePhysicalBooksService {

    private final int MAX_BOOKS = 3;
    private final int MIN_BOOKS = 0;

    private final PhysicalBookRepository physicalBookRepository;

    public GeneratePhysicalBooksService(PhysicalBookRepository physicalBookRepository) {
        this.physicalBookRepository = physicalBookRepository;
    }

    public List<PhysicalBookDto> execute(List<BookDto> bookDtos) {

        ArrayList<PhysicalBookDto> physicalBooksToCreate = new ArrayList<>();
        Random random = new Random();

        // TODO

        bookDtos.forEach( bookDto -> {
            ArrayList<PhysicalBookDto> physicalBooksForGivenBook = new ArrayList<>();
            int numberOfCopies = random.nextInt(MAX_BOOKS) + 1;
            int physicalStateOfBook = random.nextInt(PhysicalBookStateEnum.values().length);

            for(int i = 0; i<numberOfCopies; i++) {
                PhysicalBookDto physicalBookDto = new PhysicalBookDto();
                physicalBookDto.setBookDto(bookDto);
                physicalBookDto.setBookState(PhysicalBookStateEnum.values()[physicalStateOfBook]);
                physicalBookDto.setCustomerDto(null);
                physicalBookDto.setPhysicalBookId(null);
                physicalBooksForGivenBook.add(physicalBookDto);
            }

            physicalBooksToCreate.addAll(physicalBooksForGivenBook);
        });

        List<PhysicalBookEntity> physicalBookEntities = physicalBooksToCreate.stream().map(PhysicalBookDtoToEntityMapper::map).toList();
        List<PhysicalBookEntity> savedPhysicalBookEntities = physicalBookRepository.saveAll(physicalBookEntities);
        List<PhysicalBookDto> savedPhysicalBookDtos = savedPhysicalBookEntities.stream().map(PhysicalBookEntityToDtoMapper::map).toList();
        return savedPhysicalBookDtos;
    }
}
