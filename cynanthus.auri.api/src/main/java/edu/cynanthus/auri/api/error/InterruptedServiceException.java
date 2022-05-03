package edu.cynanthus.auri.api.error;

import java.util.List;

public class InterruptedServiceException extends ServiceException {

    public InterruptedServiceException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    public InterruptedServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InterruptedServiceException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    public InterruptedServiceException(String message) {
        super(message);
    }

}
