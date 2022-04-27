package edu.cynanthus.auri.api;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ServiceException extends RuntimeException {

    private final ExceptionType exceptionType;

    private final List<ExceptionRecord> records;

    public ServiceException(String message, ExceptionType exceptionType, List<ExceptionRecord> records) {
        super(Objects.requireNonNull(message));
        this.exceptionType = Objects.requireNonNull(exceptionType);
        this.records = Objects.requireNonNull(records);
    }

    public ServiceException(String message, ExceptionType exceptionType) {
        this(message, exceptionType, new LinkedList<>());
    }

    public ExceptionType getErrorType() {
        return exceptionType;
    }

    public List<ExceptionRecord> getRecords() {
        return records;
    }

    public ExceptionRecord toExceptionRecord() {
        return new ExceptionRecord(getMessage(), exceptionType, records);
    }

}
