package bookstore.mapper;

import bookstore.database_entity.PhysicalBookEntity;
import bookstore.dto.PhysicalBookDto;

public class PhysicalBookDtoToEntityMapper {
    public static PhysicalBookEntity map(PhysicalBookDto physicalBookDto) {
        PhysicalBookEntity physicalBookEntity = new PhysicalBookEntity();
        physicalBookEntity.setPhysicalBookId(physicalBookDto.getPhysicalBookId());
        physicalBookEntity.setBookEntity(BookDtoToEntityMapper.map(physicalBookDto.getBookDto()));
        physicalBookEntity.setBookState(physicalBookDto.getBookState());
        physicalBookEntity.setCustomerEntity(CustomerDtoToEntityMapper.map(physicalBookDto.getCustomerDto()));
        return physicalBookEntity;
    }
}
