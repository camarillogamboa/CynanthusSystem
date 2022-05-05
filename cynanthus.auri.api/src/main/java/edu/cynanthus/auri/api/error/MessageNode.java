package edu.cynanthus.auri.api.error;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * El tipo Message node.
 */
public final class MessageNode {

    /**
     * El Message.
     */
    private final String message;

    /**
     * El Message nodes.
     */
    private final List<MessageNode> messageNodes;

    /**
     * Instancia un nuevo Message node.
     *
     * @param message      el message
     * @param messageNodes el message nodes
     */
    public MessageNode(String message, List<MessageNode> messageNodes) {
        this.message = message;
        this.messageNodes = messageNodes;
    }

    /**
     * Instancia un nuevo Message node.
     *
     * @param message el message
     */
    public MessageNode(String message) {
        this.message = message;
        this.messageNodes = new LinkedList<>();
    }

    /**
     * Instancia un nuevo Message node.
     */
    MessageNode() {
        this("");
    }

    /**
     * Permite obtener message.
     *
     * @return el message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Permite obtener exception nodes.
     *
     * @return el exception nodes
     */
    public List<MessageNode> getExceptionNodes() {
        return messageNodes;
    }

    /**
     * Equals boolean.
     *
     * @param o el o
     * @return el boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageNode that = (MessageNode) o;
        return Objects.equals(message, that.message) && Objects.equals(messageNodes, that.messageNodes);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(message, messageNodes);
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            "message:'" + message + '\'' +
            ",messageNodes:" + messageNodes +
            '}';
    }

}
