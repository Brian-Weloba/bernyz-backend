package ke.bernys.backend.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidArgumentsException extends IllegalArgumentException {
    public InvalidArgumentsException(String message) {
        super(message);
    }
}
    