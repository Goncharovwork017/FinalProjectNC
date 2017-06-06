package by.goncharov.nc.exceptions;

/**
 * Created by ivan on 02.06.2017.
 */

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException() {
        super();
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
