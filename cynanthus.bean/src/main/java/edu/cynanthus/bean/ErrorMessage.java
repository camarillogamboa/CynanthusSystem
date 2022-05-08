package edu.cynanthus.bean;

import java.util.List;
import java.util.Objects;

public class ErrorMessage<T> {

    private final int code;
    private final String message;
    private final List<T> causes;

    public ErrorMessage(int code, String message, List<T> causes) {
        this.code = code;
        this.message = message;
        this.causes = causes;
    }

    public ErrorMessage(int code, String message) {
        this.code = code;
        this.message = message;
        this.causes = null;
    }

    ErrorMessage() {
        this.code = 0;
        this.message = "";
        this.causes = null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getCauses() {
        return causes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorMessage<?> that = (ErrorMessage<?>) o;
        return code == that.code && Objects.equals(message, that.message) && Objects.equals(causes, that.causes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, causes);
    }

    @Override
    public String toString() {
        return "{" +
            "code:" + code +
            ",message:'" + message + '\'' +
            ",causes:" + causes +
            '}';
    }

}
