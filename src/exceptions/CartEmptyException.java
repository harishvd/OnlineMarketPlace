package exceptions;

public class CartEmptyException extends RuntimeException{
    public CartEmptyException(final String message) {
        super(message);
    }
}
