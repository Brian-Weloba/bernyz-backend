package ke.bernys.backend.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The ItemNotFoundException class is a custom exception class that extends the RuntimeException
 * class
 */
@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
