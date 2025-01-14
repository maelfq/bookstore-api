package bookstore.service;

import bookstore.database_entity.PhysicalBookEntity;
import bookstore.dto.PhysicalBookDto;
import bookstore.mapper.PhysicalBookEntityToDtoMapper;
import bookstore.repository.PhysicalBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindPhysicalBooksByBookIdService {

    private final PhysicalBookRepository physicalBookRepository;

    public FindPhysicalBooksByBookIdService(PhysicalBookRepository physicalBookRepository) {
        this.physicalBookRepository = physicalBookRepository;
    }

    public List<PhysicalBookDto> execute(Long bookId) {
        List<PhysicalBookEntity> physicalBooks = this.physicalBookRepository.findByBookEntity_BookId(bookId);
        List<PhysicalBookDto> physicalBookDtos = physicalBooks.stream().map(PhysicalBookEntityToDtoMapper::map).toList();
        return physicalBookDtos;
    }
}
