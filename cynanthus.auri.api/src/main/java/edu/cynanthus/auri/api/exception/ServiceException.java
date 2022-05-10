package edu.cynanthus.auri.api.exception;

/**
 * El tipo Service exception.
 */
public class ServiceException extends RuntimeException {

    /**
     * Instancia un nuevo Service exception.
     */
    public ServiceException() {
    }

    /**
     * Instancia un nuevo Service exception.
     *
     * @param message el message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Instancia un nuevo Service exception.
     *
     * @param message el message
     * @param cause   el cause
     */
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instancia un nuevo Service exception.
     *
     * @param cause el cause
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * Instancia un nuevo Service exception.
     *
     * @param message            el message
     * @param cause              el cause
     * @param enableSuppression  el enable suppression
     * @param writableStackTrace el writable stack trace
     */
    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
