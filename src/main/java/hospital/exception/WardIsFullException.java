package hospital.exception;

public class WardIsFullException extends RuntimeException{
    public WardIsFullException(String message) {
        super(message);
    }
}
