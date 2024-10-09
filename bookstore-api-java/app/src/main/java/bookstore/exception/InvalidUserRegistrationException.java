package bookstore.exception;

import org.springframework.http.HttpStatus;

public class InvalidUserRegistrationException extends AbstractExceptionWithHttpErrorStatus {
    public InvalidUserRegistrationException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpErrorStatus() {
        return HttpStatus.BAD_REQUEST;
    }

}
