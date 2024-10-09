package bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bookstore.dto.PhysicalBookDto;
import bookstore.exception.UserDoesNotExistExceptionInterface;
import bookstore.service.UpdatePhysicalBookService;

@CrossOrigin
@RestController
@RequestMapping("/api/bookstore/book/physical/rent")
public class UpdatePhysicalBookController {
    public final UpdatePhysicalBookService rentPhysicalBookService;

    public UpdatePhysicalBookController(UpdatePhysicalBookService service) {
            this.rentPhysicalBookService = service;
    }

    @PutMapping
    public ResponseEntity rentPhysicalBookController(@RequestBody PhysicalBookDto physicalBookDto) {
        PhysicalBookDto physicalBookDtoUpdated;
        try {
            physicalBookDtoUpdated = this.rentPhysicalBookService.execute(physicalBookDto);
        }
        catch(UserDoesNotExistExceptionInterface e) {
            return new ResponseEntity(e, e.getHttpErrorStatus());
        }
        return new ResponseEntity(physicalBookDtoUpdated, HttpStatus.OK);
    }
}
