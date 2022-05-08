package edu.cynanthus.auri.api.exception;

import edu.cynanthus.bean.ErrorMessage;

public class WebServiceException extends ServiceException {

    private final ErrorMessage<String> errorMessage;

    public WebServiceException(ErrorMessage<String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public WebServiceException(String message, ErrorMessage<String> errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    public WebServiceException(String message, Throwable cause, ErrorMessage<String> errorMessage) {
        super(message, cause);
        this.errorMessage = errorMessage;
    }

    public WebServiceException(Throwable cause, ErrorMessage<String> errorMessage) {
        super(cause);
        this.errorMessage = errorMessage;
    }

    public WebServiceException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace,
        ErrorMessage<String> errorMessage
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorMessage = errorMessage;
    }

    public ErrorMessage<String> getErrorMessage() {
        return errorMessage;
    }

}
