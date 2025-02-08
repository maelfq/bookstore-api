package bookstore.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionWithHttpErrorStatusInterface {
    HttpStatus getHttpErrorStatus();
}
