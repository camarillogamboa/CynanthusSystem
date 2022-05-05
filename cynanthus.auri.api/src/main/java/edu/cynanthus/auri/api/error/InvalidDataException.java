package edu.cynanthus.auri.api.error;

import java.util.List;

/**
 * El tipo Invalid data exception.
 */
public class InvalidDataException extends ServiceException {

    /**
     * Instancia un nuevo Invalid data exception.
     *
     * @param message   el message
     * @param throwable el throwable
     * @param nodes     el nodes
     */
    public InvalidDataException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    /**
     * Instancia un nuevo Invalid data exception.
     *
     * @param message   el message
     * @param throwable el throwable
     */
    public InvalidDataException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instancia un nuevo Invalid data exception.
     *
     * @param message el message
     * @param nodes   el nodes
     */
    public InvalidDataException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    /**
     * Instancia un nuevo Invalid data exception.
     *
     * @param message el message
     */
    public InvalidDataException(String message) {
        super(message);
    }

}
