package bookstore.repository;


import bookstore.database_entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<String, BookEntity> {
}
