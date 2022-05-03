package edu.cynanthus.auri.api.error;

import java.util.List;

public class IncompatibleServiceException extends ServiceException{

    public IncompatibleServiceException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    public IncompatibleServiceException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public IncompatibleServiceException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    public IncompatibleServiceException(String message) {
        super(message);
    }

}
