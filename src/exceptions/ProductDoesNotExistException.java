package src.exceptions;

public class ProductDoesNotExistException extends RuntimeException {
    public ProductDoesNotExistException(String message, Throwable err) {
        super(message, err);
    }
}
