package edu.cynanthus.auri.api.error;

import java.util.List;

/**
 * El tipo Remote service exception.
 */
public class RemoteServiceException extends ServiceException {

    /**
     * Instancia un nuevo Remote service exception.
     *
     * @param message   el message
     * @param throwable el throwable
     * @param nodes     el nodes
     */
    public RemoteServiceException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    /**
     * Instancia un nuevo Remote service exception.
     *
     * @param message   el message
     * @param throwable el throwable
     */
    public RemoteServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instancia un nuevo Remote service exception.
     *
     * @param message el message
     * @param nodes   el nodes
     */
    public RemoteServiceException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    /**
     * Instancia un nuevo Remote service exception.
     *
     * @param message el message
     */
    public RemoteServiceException(String message) {
        super(message);
    }

}
