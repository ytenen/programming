package Exeptions;

public class ZeroPassengersException extends RuntimeException {
    public ZeroPassengersException(String message) {
        super(message);
    }
}
