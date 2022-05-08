package edu.cynanthus.auri.api.exception;

public class WebClientException extends ServiceException {

    public WebClientException() {
    }

    public WebClientException(String message) {
        super(message);
    }

    public WebClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebClientException(Throwable cause) {
        super(cause);
    }

    public WebClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
