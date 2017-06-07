package web.controllers.exceptions;


public class NoReadsException extends RuntimeException {

    public NoReadsException() {
    }

    public NoReadsException(String message) {
        super(message);
    }
}
