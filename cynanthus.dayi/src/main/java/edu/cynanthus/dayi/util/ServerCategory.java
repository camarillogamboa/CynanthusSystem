package edu.cynanthus.dayi.util;

import edu.cynanthus.domain.ServerInfo;

import java.util.List;
import java.util.Objects;

public final class ServerCategory {

    private final String name;
    private final List<ServerInfo> servers;

    public ServerCategory(String name, List<ServerInfo> servers) {
        this.name = name;
        this.servers = servers;
    }

    public String getName() {
        return name;
    }

    public List<ServerInfo> getServers() {
        return servers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerCategory that = (ServerCategory) o;
        return Objects.equals(name, that.name) && Objects.equals(servers, that.servers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, servers);
    }

    @Override
    public String toString() {
        return "{" +
            "categoryName:'" + name + '\'' +
            ",servers:" + servers +
            '}';
    }

}
