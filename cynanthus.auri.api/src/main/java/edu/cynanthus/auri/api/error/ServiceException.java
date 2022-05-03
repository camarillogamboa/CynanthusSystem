package edu.cynanthus.auri.api.error;

import java.util.LinkedList;
import java.util.List;

/**
 * El tipo AuriService exception.
 */
public class ServiceException extends RuntimeException {

    /**
     * El Records.
     */
    private final List<MessageNode> nodes;

    /**
     * Instancia un nuevo AuriService exception.
     *
     * @param message el message
     * @param nodes   el records
     */
    public ServiceException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable);
        this.nodes = nodes;
    }

    public ServiceException(String message, Throwable throwable) {
        super(message, throwable);
        this.nodes = new LinkedList<>();
    }

    public ServiceException(String message, List<MessageNode> nodes) {
        super(message);
        this.nodes = nodes;
    }

    public ServiceException(String message) {
        super(message);
        this.nodes = new LinkedList<>();
    }

    /**
     * Permite obtener records.
     *
     * @return el records
     */
    public List<MessageNode> getNodes() {
        return nodes;
    }

    /**
     * To exception record exception record.
     *
     * @return el exception record
     */
    public MessageNode toExceptionNode() {
        return new MessageNode(getMessage(), nodes);
    }

}
