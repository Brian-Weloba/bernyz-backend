package ke.bernys.backend.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The ProductNotFoundException class is a custom exception class that extends the RuntimeException
 * class
 */
@ResponseStatus(org.springframework.http.HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
