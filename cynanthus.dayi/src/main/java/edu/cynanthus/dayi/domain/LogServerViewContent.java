package edu.cynanthus.dayi.domain;

import edu.cynanthus.domain.ServerInfo;

import java.util.Arrays;

public class LogServerViewContent extends ServerViewContent {

    private final String[] logFileNames;

    public LogServerViewContent(ServerInfo serverInfo, Boolean available, String[] logFileNames) {
        super(serverInfo, available);
        this.logFileNames = logFileNames;
    }

    public String[] getLogFileNames() {
        return logFileNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LogServerViewContent that = (LogServerViewContent) o;
        return Arrays.equals(logFileNames, that.logFileNames);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(logFileNames);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
            super.toString() + "," +
            "logFileNames:" + Arrays.toString(logFileNames) + '}';
    }

}
