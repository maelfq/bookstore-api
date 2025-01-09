package bookstore.repository;

import bookstore.database_entity.PhysicalBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalBookRepository extends JpaRepository<PhysicalBookEntity, Long> {
}
