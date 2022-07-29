package edu.cynanthus.auri.api.exception;

/**
 * Excepción diseñada para
 */
public class InvalidArgumentException extends ServiceException {

    /**
     * Instancia un nuevo Invalid argument exception.
     */
    public InvalidArgumentException() {
    }

    /**
     * Instancia un nuevo Invalid argument exception.
     *
     * @param message el message
     */
    public InvalidArgumentException(String message) {
        super(message);
    }

    /**
     * Instancia un nuevo Invalid argument exception.
     *
     * @param message el message
     * @param cause   el cause
     */
    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instancia un nuevo Invalid argument exception.
     *
     * @param cause el cause
     */
    public InvalidArgumentException(Throwable cause) {
        super(cause);
    }

    /**
     * Instancia un nuevo Invalid argument exception.
     *
     * @param message            el message
     * @param cause              el cause
     * @param enableSuppression  el enable suppression
     * @param writableStackTrace el writable stack trace
     */
    public InvalidArgumentException(
        String message,
        Throwable cause,
        boolean enableSuppression,
        boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
