package ua.nure.huzhyn.exception;

public class DBException extends RuntimeException {
    private String httpStatusCode;

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

}
