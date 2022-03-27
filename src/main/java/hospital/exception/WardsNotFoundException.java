package hospital.exception;

public class WardsNotFoundException extends RuntimeException{
    public WardsNotFoundException(String message) {
        super(message);
    }
}
