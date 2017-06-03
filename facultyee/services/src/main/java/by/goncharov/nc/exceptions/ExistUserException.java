package by.goncharov.nc.exceptions;

/**
 * Created by ivan on 03.06.2017.
 */
public class ExistUserException extends RuntimeException {

    public ExistUserException() {
        super();
    }

    public ExistUserException(String message) {
        super(message);
    }
}

