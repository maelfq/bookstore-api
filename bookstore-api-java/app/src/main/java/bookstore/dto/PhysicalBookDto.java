package bookstore.dto;

public class PhysicalBookDto {
    private Long physicalBookId;
    private PhysicalBookStateEnum bookState;
    private BookDto bookDto;
    private CustomerDto customerDto;

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

    public BookDto getBookDto() {
        return bookDto;
    }

    public void setBookDto(BookDto bookDto) {
        this.bookDto = bookDto;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public void setCustomerDto(CustomerDto customerDto) {
        this.customerDto = customerDto;
    }
}
