package edu.cynanthus.auri.api.exception;

/**
 * El tipo Syntax exception.
 */
public class SyntaxException extends ServiceException {

    /**
     * Instancia un nuevo Syntax exception.
     */
    public SyntaxException() {
    }

    /**
     * Instancia un nuevo Syntax exception.
     *
     * @param message el message
     */
    public SyntaxException(String message) {
        super(message);
    }

    /**
     * Instancia un nuevo Syntax exception.
     *
     * @param message el message
     * @param cause   el cause
     */
    public SyntaxException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instancia un nuevo Syntax exception.
     *
     * @param cause el cause
     */
    public SyntaxException(Throwable cause) {
        super(cause);
    }

    /**
     * Instancia un nuevo Syntax exception.
     *
     * @param message            el message
     * @param cause              el cause
     * @param enableSuppression  el enable suppression
     * @param writableStackTrace el writable stack trace
     */
    public SyntaxException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
