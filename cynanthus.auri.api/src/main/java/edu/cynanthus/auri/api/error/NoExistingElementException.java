package edu.cynanthus.auri.api.error;

import java.util.List;

public class NoExistingElementException extends ServiceException {

    public NoExistingElementException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    public NoExistingElementException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public NoExistingElementException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    public NoExistingElementException(String message) {
        super(message);
    }

}
