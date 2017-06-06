package web.controllers.exceptions;

/**
 * Created by ivan on 02.06.2017.
 */

public class NoReadsException extends RuntimeException {

    public NoReadsException() {
    }

    public NoReadsException(String message) {
        super(message);
    }
}
