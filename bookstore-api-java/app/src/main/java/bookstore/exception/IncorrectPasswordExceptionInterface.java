package bookstore.exception;

import org.springframework.http.HttpStatus;

public class IncorrectPasswordExceptionInterface extends AbstractExceptionWithHttpErrorStatus {

    public IncorrectPasswordExceptionInterface(String message) {
      super(message);
    }

    @Override
    public HttpStatus getHttpErrorStatus() {
      return HttpStatus.UNAUTHORIZED;
    }
}
