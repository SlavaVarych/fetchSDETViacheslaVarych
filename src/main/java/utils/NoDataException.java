package utils;

/**
 * This class throws exception with custom message
 */
public class NoDataException extends RuntimeException{
    public NoDataException(String message) {
        super(message);
    }
}
