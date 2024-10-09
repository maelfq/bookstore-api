package bookstore.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends AbstractExceptionWithHttpErrorStatus {

    public UserAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpErrorStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
