package edu.cynanthus.auri.api.exception;

/**
 * El tipo Invalid type exception.
 */
public class InvalidTypeException extends ServiceException {

    /**
     * Instancia un nuevo Invalid type exception.
     */
    public InvalidTypeException() {
    }

    /**
     * Instancia un nuevo Invalid type exception.
     *
     * @param message el message
     */
    public InvalidTypeException(String message) {
        super(message);
    }

    /**
     * Instancia un nuevo Invalid type exception.
     *
     * @param message el message
     * @param cause   el cause
     */
    public InvalidTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instancia un nuevo Invalid type exception.
     *
     * @param cause el cause
     */
    public InvalidTypeException(Throwable cause) {
        super(cause);
    }

    /**
     * Instancia un nuevo Invalid type exception.
     *
     * @param message            el message
     * @param cause              el cause
     * @param enableSuppression  el enable suppression
     * @param writableStackTrace el writable stack trace
     */
    public InvalidTypeException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
