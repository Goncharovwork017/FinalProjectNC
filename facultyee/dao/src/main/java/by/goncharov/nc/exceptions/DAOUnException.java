package by.goncharov.nc.exceptions;

/**
 * Created by ivan on 03.05.2017.
 */
public class DAOUnException extends RuntimeException {

    public DAOUnException() {
    }

    public DAOUnException(String message) {
        super(message);
    }

    public DAOUnException(Exception e) {

            super(e);
    }
}
