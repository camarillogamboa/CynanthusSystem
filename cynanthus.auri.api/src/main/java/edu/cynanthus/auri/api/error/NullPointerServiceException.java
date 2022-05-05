package edu.cynanthus.auri.api.error;

import java.util.List;

/**
 * El tipo Null pointer service exception.
 */
public class NullPointerServiceException extends ServiceException {

    /**
     * Instancia un nuevo Null pointer service exception.
     *
     * @param message   el message
     * @param throwable el throwable
     * @param nodes     el nodes
     */
    public NullPointerServiceException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    /**
     * Instancia un nuevo Null pointer service exception.
     *
     * @param message   el message
     * @param throwable el throwable
     */
    public NullPointerServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instancia un nuevo Null pointer service exception.
     *
     * @param message el message
     * @param nodes   el nodes
     */
    public NullPointerServiceException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    /**
     * Instancia un nuevo Null pointer service exception.
     *
     * @param message el message
     */
    public NullPointerServiceException(String message) {
        super(message);
    }

}
