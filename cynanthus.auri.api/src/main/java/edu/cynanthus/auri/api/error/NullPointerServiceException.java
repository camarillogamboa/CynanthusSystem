package edu.cynanthus.auri.api.error;

import java.util.List;

public class NullPointerServiceException extends ServiceException {

    public NullPointerServiceException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    public NullPointerServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public NullPointerServiceException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    public NullPointerServiceException(String message) {
        super(message);
    }

}
