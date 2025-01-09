package bookstore.database_entity;

import bookstore.dto.PhysicalBookStateEnum;
import jakarta.persistence.*;

@Entity
public class PhysicalBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long physicalBookId;

    private PhysicalBookStateEnum bookState;

    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity bookEntity;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customer_id", nullable = true)
    private CustomerEntity customerEntity;

    public Long getPhysicalBookId() {
        return physicalBookId;
    }

    public void setPhysicalBookId(Long physicalBookId) {
        this.physicalBookId = physicalBookId;
    }

    public PhysicalBookStateEnum getBookState() {
        return bookState;
    }

    public void setBookState(PhysicalBookStateEnum bookState) {
        this.bookState = bookState;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
    }
}
