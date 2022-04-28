package edu.cynanthus.auri.api;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * El tipo Service exception.
 */
public class ServiceException extends RuntimeException {

    /**
     * El Exception type.
     */
    private final ExceptionType exceptionType;

    /**
     * El Records.
     */
    private final List<ExceptionRecord> records;

    /**
     * Instancia un nuevo Service exception.
     *
     * @param message       el message
     * @param exceptionType el exception type
     * @param records       el records
     */
    public ServiceException(String message, ExceptionType exceptionType, List<ExceptionRecord> records) {
        super(Objects.requireNonNull(message));
        this.exceptionType = Objects.requireNonNull(exceptionType);
        this.records = Objects.requireNonNull(records);
    }

    /**
     * Instancia un nuevo Service exception.
     *
     * @param message       el message
     * @param exceptionType el exception type
     */
    public ServiceException(String message, ExceptionType exceptionType) {
        this(message, exceptionType, new LinkedList<>());
    }

    /**
     * Permite obtener error type.
     *
     * @return el error type
     */
    public ExceptionType getErrorType() {
        return exceptionType;
    }

    /**
     * Permite obtener records.
     *
     * @return el records
     */
    public List<ExceptionRecord> getRecords() {
        return records;
    }

    /**
     * To exception record exception record.
     *
     * @return el exception record
     */
    public ExceptionRecord toExceptionRecord() {
        return new ExceptionRecord(getMessage(), exceptionType, records);
    }

}
