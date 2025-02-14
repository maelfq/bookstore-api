package bookstore.repository;

import bookstore.database_entity.PhysicalBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysicalBookRepository extends JpaRepository<PhysicalBookEntity, Long> {
    List<PhysicalBookEntity> findByBookEntity_BookId(Long bookId);

    List<PhysicalBookEntity> findByCustomerEntity_Email(String customerEmail);
 }
