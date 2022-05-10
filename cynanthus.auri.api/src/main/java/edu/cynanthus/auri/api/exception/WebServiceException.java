package edu.cynanthus.auri.api.exception;

import edu.cynanthus.bean.ErrorMessage;

/**
 * El tipo Web service exception.
 */
public class WebServiceException extends ServiceException {

    /**
     * El Error message.
     */
    private final ErrorMessage<String> errorMessage;

    /**
     * Instancia un nuevo Web service exception.
     *
     * @param errorMessage el error message
     */
    public WebServiceException(ErrorMessage<String> errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Instancia un nuevo Web service exception.
     *
     * @param message      el message
     * @param errorMessage el error message
     */
    public WebServiceException(String message, ErrorMessage<String> errorMessage) {
        super(message);
        this.errorMessage = errorMessage;
    }

    /**
     * Instancia un nuevo Web service exception.
     *
     * @param message      el message
     * @param cause        el cause
     * @param errorMessage el error message
     */
    public WebServiceException(String message, Throwable cause, ErrorMessage<String> errorMessage) {
        super(message, cause);
        this.errorMessage = errorMessage;
    }

    /**
     * Instancia un nuevo Web service exception.
     *
     * @param cause        el cause
     * @param errorMessage el error message
     */
    public WebServiceException(Throwable cause, ErrorMessage<String> errorMessage) {
        super(cause);
        this.errorMessage = errorMessage;
    }

    /**
     * Instancia un nuevo Web service exception.
     *
     * @param message            el message
     * @param cause              el cause
     * @param enableSuppression  el enable suppression
     * @param writableStackTrace el writable stack trace
     * @param errorMessage       el error message
     */
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

    /**
     * Permite obtener error message.
     *
     * @return el error message
     */
    public ErrorMessage<String> getErrorMessage() {
        return errorMessage;
    }

}
