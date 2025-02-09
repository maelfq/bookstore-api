package bookstore.exception;

import org.springframework.http.HttpStatus;

public class AbstractExceptionWithHttpErrorStatus extends RuntimeException implements ExceptionWithHttpErrorStatusInterface {

    private HttpStatus httpStatus = getHttpErrorStatus();

    public AbstractExceptionWithHttpErrorStatus(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpErrorStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
