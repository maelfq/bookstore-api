package bookstore.exception;

import org.springframework.http.HttpStatus;

public class UserDoesNotExistExceptionInterface extends AbstractExceptionWithHttpErrorStatus {

    public UserDoesNotExistExceptionInterface(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpErrorStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
