package ua.nure.huzhyn.exception;

public class UnauthorizedException extends ServiceException {

    public UnauthorizedException(String message) {
        super("401", message);
    }
}
