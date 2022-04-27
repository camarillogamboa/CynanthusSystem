package edu.cynanthus.auri.api;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ExceptionRecord {

    private final String message;

    private final ExceptionType exceptionType;

    private final List<ExceptionRecord> subErrors;

    public ExceptionRecord(String message, ExceptionType exceptionType, List<ExceptionRecord> subErrors) {
        this.message = message;
        this.exceptionType = exceptionType;
        this.subErrors = new LinkedList<>();
    }

    public ExceptionRecord(String message, ExceptionType exceptionType) {
        this(message, exceptionType, new LinkedList<>());
    }

    ExceptionRecord() {
        this(null, null);
    }

    public String getMessage() {
        return message;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }

    public List<ExceptionRecord> getSubErrors() {
        return subErrors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExceptionRecord that = (ExceptionRecord) o;
        return Objects.equals(message, that.message) &&
            exceptionType == that.exceptionType &&
            Objects.equals(subErrors, that.subErrors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, exceptionType, subErrors);
    }

    @Override
    public String toString() {
        return "{" +
            "message:'" + message + '\'' +
            ",exceptionType:" + exceptionType +
            ",subExceptions:" + subErrors +
            '}';
    }

}
