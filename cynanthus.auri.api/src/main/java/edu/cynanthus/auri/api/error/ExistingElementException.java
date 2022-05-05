package edu.cynanthus.auri.api.error;

import java.util.List;

/**
 * El tipo Existing element exception.
 */
public class ExistingElementException extends ServiceException {

    /**
     * Instancia un nuevo Existing element exception.
     *
     * @param message   el message
     * @param throwable el throwable
     * @param nodes     el nodes
     */
    public ExistingElementException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    /**
     * Instancia un nuevo Existing element exception.
     *
     * @param message   el message
     * @param throwable el throwable
     */
    public ExistingElementException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instancia un nuevo Existing element exception.
     *
     * @param message el message
     * @param nodes   el nodes
     */
    public ExistingElementException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    /**
     * Instancia un nuevo Existing element exception.
     *
     * @param message el message
     */
    public ExistingElementException(String message) {
        super(message);
    }

}
