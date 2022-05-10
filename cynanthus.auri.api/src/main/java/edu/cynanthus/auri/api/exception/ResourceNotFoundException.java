package edu.cynanthus.auri.api.exception;

/**
 * El tipo Resource not found exception.
 */
public class ResourceNotFoundException extends ServiceException {

    /**
     * Instancia un nuevo Resource not found exception.
     */
    public ResourceNotFoundException() {
    }

    /**
     * Instancia un nuevo Resource not found exception.
     *
     * @param message el message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * Instancia un nuevo Resource not found exception.
     *
     * @param message el message
     * @param cause   el cause
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instancia un nuevo Resource not found exception.
     *
     * @param cause el cause
     */
    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Instancia un nuevo Resource not found exception.
     *
     * @param message            el message
     * @param cause              el cause
     * @param enableSuppression  el enable suppression
     * @param writableStackTrace el writable stack trace
     */
    public ResourceNotFoundException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
