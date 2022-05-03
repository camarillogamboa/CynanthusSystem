package edu.cynanthus.auri.api.error;

import java.util.List;

public class RemoteServiceException extends ServiceException {

    public RemoteServiceException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    public RemoteServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public RemoteServiceException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    public RemoteServiceException(String message) {
        super(message);
    }

}
