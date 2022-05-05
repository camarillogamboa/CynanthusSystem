package edu.cynanthus.auri.api.error;

import java.util.List;

/**
 * El tipo Io service exception.
 */
public class IOServiceException extends ServiceException {

    /**
     * Instancia un nuevo Io service exception.
     *
     * @param message   el message
     * @param throwable el throwable
     * @param nodes     el nodes
     */
    public IOServiceException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    /**
     * Instancia un nuevo Io service exception.
     *
     * @param message   el message
     * @param throwable el throwable
     */
    public IOServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instancia un nuevo Io service exception.
     *
     * @param message el message
     * @param nodes   el nodes
     */
    public IOServiceException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    /**
     * Instancia un nuevo Io service exception.
     *
     * @param message el message
     */
    public IOServiceException(String message) {
        super(message);
    }

}
