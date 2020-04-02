package ua.nure.huzhyn.exception;

public class DataBaseException extends ServiceException {

    private String httpStatusCode;

    public DataBaseException() {
        super("500");
    }

    public DataBaseException(String message) {
        super("500", message);
    }

    public DataBaseException(String message, Throwable cause) {
        super("500", message, cause);
    }
}
