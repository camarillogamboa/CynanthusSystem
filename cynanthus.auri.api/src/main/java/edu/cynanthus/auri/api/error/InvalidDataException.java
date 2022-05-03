package edu.cynanthus.auri.api.error;

import java.util.List;

public class InvalidDataException extends ServiceException {

    public InvalidDataException(String message, Throwable throwable, List<MessageNode> nodes) {
        super(message, throwable, nodes);
    }

    public InvalidDataException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InvalidDataException(String message, List<MessageNode> nodes) {
        super(message, nodes);
    }

    public InvalidDataException(String message) {
        super(message);
    }

}
