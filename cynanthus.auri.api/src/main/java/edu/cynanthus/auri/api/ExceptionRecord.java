package edu.cynanthus.auri.api;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * El tipo Exception record.
 */
public class ExceptionRecord {

    /**
     * El Message.
     */
    private final String message;

    /**
     * El Exception type.
     */
    private final ExceptionType exceptionType;

    /**
     * El Sub errors.
     */
    private final List<ExceptionRecord> subErrors;

    /**
     * Instancia un nuevo Exception record.
     *
     * @param message       el message
     * @param exceptionType el exception type
     * @param subErrors     el sub errors
     */
    public ExceptionRecord(String message, ExceptionType exceptionType, List<ExceptionRecord> subErrors) {
        this.message = message;
        this.exceptionType = exceptionType;
        this.subErrors = new LinkedList<>();
    }

    /**
     * Instancia un nuevo Exception record.
     *
     * @param message       el message
     * @param exceptionType el exception type
     */
    public ExceptionRecord(String message, ExceptionType exceptionType) {
        this(message, exceptionType, new LinkedList<>());
    }

    /**
     * Instancia un nuevo Exception record.
     */
    ExceptionRecord() {
        this(null, null);
    }

    /**
     * Permite obtener message.
     *
     * @return el message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Permite obtener exception type.
     *
     * @return el exception type
     */
    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    /**
     * Permite obtener sub errors.
     *
     * @return el sub errors
     */
    public List<ExceptionRecord> getSubErrors() {
        return subErrors;
    }

    /**
     * Equals boolean.
     *
     * @param o el o
     * @return el boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionRecord that = (ExceptionRecord) o;
        return Objects.equals(message, that.message) &&
            exceptionType == that.exceptionType &&
            Objects.equals(subErrors, that.subErrors);
    }

    /**
     * Hash code int.
     *
     * @return el int
     */
    @Override
    public int hashCode() {
        return Objects.hash(message, exceptionType, subErrors);
    }

    /**
     * To string string.
     *
     * @return el string
     */
    @Override
    public String toString() {
        return "{" +
            "message:'" + message + '\'' +
            ",exceptionType:" + exceptionType +
            ",subExceptions:" + subErrors +
            '}';
    }

}
