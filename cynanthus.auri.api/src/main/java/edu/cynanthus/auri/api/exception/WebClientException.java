package edu.cynanthus.auri.api.exception;

/**
 * El tipo Web client exception.
 */
public class WebClientException extends ServiceException {

    /**
     * Instancia un nuevo Web client exception.
     */
    public WebClientException() {
    }

    /**
     * Instancia un nuevo Web client exception.
     *
     * @param message el message
     */
    public WebClientException(String message) {
        super(message);
    }

    /**
     * Instancia un nuevo Web client exception.
     *
     * @param message el message
     * @param cause   el cause
     */
    public WebClientException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instancia un nuevo Web client exception.
     *
     * @param cause el cause
     */
    public WebClientException(Throwable cause) {
        super(cause);
    }

    /**
     * Instancia un nuevo Web client exception.
     *
     * @param message            el message
     * @param cause              el cause
     * @param enableSuppression  el enable suppression
     * @param writableStackTrace el writable stack trace
     */
    public WebClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
