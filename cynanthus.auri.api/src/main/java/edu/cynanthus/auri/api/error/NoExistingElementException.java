package edu.cynanthus.auri.api.error;

import java.util.List;

/**
 * El tipo No existing element exception.
 */
public class NoExistingElementException extends ServiceException {

    /**
     * Instancia un nuevo No existing element exception.
     *
     * @param message   el message
     * @param throwable el throwable
     * @param nodes     el nodes
     */
    public NoExistingElementException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    /**
     * Instancia un nuevo No existing element exception.
     *
     * @param message   el message
     * @param throwable el throwable
     */
    public NoExistingElementException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instancia un nuevo No existing element exception.
     *
     * @param message el message
     * @param nodes   el nodes
     */
    public NoExistingElementException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    /**
     * Instancia un nuevo No existing element exception.
     *
     * @param message el message
     */
    public NoExistingElementException(String message) {
        super(message);
    }

}
