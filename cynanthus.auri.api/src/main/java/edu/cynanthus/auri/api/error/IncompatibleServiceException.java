package edu.cynanthus.auri.api.error;

import java.util.List;

/**
 * El tipo Incompatible service exception.
 */
public class IncompatibleServiceException extends ServiceException {

    /**
     * Instancia un nuevo Incompatible service exception.
     *
     * @param message   el message
     * @param throwable el throwable
     * @param nodes     el nodes
     */
    public IncompatibleServiceException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    /**
     * Instancia un nuevo Incompatible service exception.
     *
     * @param message   el message
     * @param throwable el throwable
     */
    public IncompatibleServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instancia un nuevo Incompatible service exception.
     *
     * @param message el message
     * @param nodes   el nodes
     */
    public IncompatibleServiceException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    /**
     * Instancia un nuevo Incompatible service exception.
     *
     * @param message el message
     */
    public IncompatibleServiceException(String message) {
        super(message);
    }

}
