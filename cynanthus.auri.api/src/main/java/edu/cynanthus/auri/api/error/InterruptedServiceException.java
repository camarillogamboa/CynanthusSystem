package edu.cynanthus.auri.api.error;

import java.util.List;

/**
 * El tipo Interrupted service exception.
 */
public class InterruptedServiceException extends ServiceException {

    /**
     * Instancia un nuevo Interrupted service exception.
     *
     * @param message   el message
     * @param throwable el throwable
     * @param nodes     el nodes
     */
    public InterruptedServiceException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    /**
     * Instancia un nuevo Interrupted service exception.
     *
     * @param message   el message
     * @param throwable el throwable
     */
    public InterruptedServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instancia un nuevo Interrupted service exception.
     *
     * @param message el message
     * @param nodes   el nodes
     */
    public InterruptedServiceException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    /**
     * Instancia un nuevo Interrupted service exception.
     *
     * @param message el message
     */
    public InterruptedServiceException(String message) {
        super(message);
    }

}
