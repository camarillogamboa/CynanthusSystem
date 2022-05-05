package edu.cynanthus.auri.api.error;

import java.util.List;

public class InvalidOperationException extends ServiceException {

    public InvalidOperationException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    public InvalidOperationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InvalidOperationException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    public InvalidOperationException(String message) {
        super(message);
    }

}
