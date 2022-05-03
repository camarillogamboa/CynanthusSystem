package edu.cynanthus.auri.api.error;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class MessageNode {

    private final String message;

    private final List<MessageNode> messageNodes;

    public MessageNode(String message, List<MessageNode> messageNodes) {
        this.message = message;
        this.messageNodes = messageNodes;
    }

    public MessageNode(String message) {
        this.message = message;
        this.messageNodes = new LinkedList<>();
    }

    MessageNode() {
        this("");
    }

    public String getMessage() {
        return message;
    }

    public List<MessageNode> getExceptionNodes() {
        return messageNodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageNode that = (MessageNode) o;
        return Objects.equals(message, that.message) && Objects.equals(messageNodes, that.messageNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, messageNodes);
    }

    @Override
    public String toString() {
        return "{" +
            "message:'" + message + '\'' +
            ",messageNodes:" + messageNodes +
            '}';
    }

}
