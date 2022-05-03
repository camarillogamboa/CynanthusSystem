package edu.cynanthus.auri.api.error;

import java.util.List;

public class ExistingElementException extends ServiceException {

    public ExistingElementException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    public ExistingElementException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ExistingElementException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    public ExistingElementException(String message) {
        super(message);
    }

}
