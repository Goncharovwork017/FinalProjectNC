package by.goncharov.nc.exceptions;

/**
 * Created by ivan on 03.06.2017.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
