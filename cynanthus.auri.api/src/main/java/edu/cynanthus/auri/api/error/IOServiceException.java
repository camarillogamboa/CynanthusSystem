package edu.cynanthus.auri.api.error;

import java.util.List;

public class IOServiceException extends ServiceException {

    public IOServiceException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    public IOServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public IOServiceException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    public IOServiceException(String message) {
        super(message);
    }

}
