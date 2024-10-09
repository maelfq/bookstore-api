package bookstore.mapper;

import bookstore.database_entity.PhysicalBookEntity;
import bookstore.dto.PhysicalBookDto;

public class PhysicalBookEntityToDtoMapper {
    public static PhysicalBookDto map(PhysicalBookEntity physicalBookEntity) {
        PhysicalBookDto physicalBookDto = new PhysicalBookDto();
        physicalBookDto.setPhysicalBookId(physicalBookEntity.getPhysicalBookId());
        physicalBookDto.setBookDto(BookEntityToDtoMapper.map(physicalBookEntity.getBookEntity()));
        physicalBookDto.setBookState(physicalBookEntity.getBookState());
        physicalBookDto.setCustomerDto(CustomerEntityToDtoMapper.map(physicalBookEntity.getCustomerEntity()));
        return physicalBookDto;
    }
}
