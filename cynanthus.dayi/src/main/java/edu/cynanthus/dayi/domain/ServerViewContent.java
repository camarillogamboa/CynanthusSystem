package edu.cynanthus.dayi.domain;

import edu.cynanthus.domain.ServerInfo;

import java.util.Objects;

public class ServerViewContent {

    private ServerInfo serverInfo;
    private Boolean available;

    public ServerViewContent(ServerInfo serverInfo, Boolean available) {
        this.serverInfo = serverInfo;
        this.available = available;
    }

    public ServerViewContent() {
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public void setServerInfo(ServerInfo serverInfo) {
        this.serverInfo = serverInfo;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerViewContent that = (ServerViewContent) o;
        return Objects.equals(serverInfo, that.serverInfo) && Objects.equals(available, that.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serverInfo, available);
    }

    @Override
    public String toString() {
        return "{" +
            "serverInfo:" + serverInfo +
            ",available:" + available +
            '}';
    }

}
