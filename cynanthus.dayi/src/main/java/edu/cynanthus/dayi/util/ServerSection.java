package edu.cynanthus.dayi.util;

import edu.cynanthus.domain.ServerInfo;

import java.util.List;
import java.util.Objects;

public final class ServerSection {

    private final String name;

    private final String iconName;
    private final List<ServerInfo> servers;

    public ServerSection(String name, String iconName, List<ServerInfo> servers) {
        this.name = name;
        this.iconName = iconName;
        this.servers = servers;
    }

    public String getName() {
        return name;
    }

    public String getIconName() {
        return iconName;
    }

    public List<ServerInfo> getServers() {
        return servers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerSection that = (ServerSection) o;
        return Objects.equals(name, that.name)
            && Objects.equals(iconName, that.iconName)
            && Objects.equals(servers, that.servers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, iconName, servers);
    }

    @Override
    public String toString() {
        return "{" +
            "name:'" + name + '\'' +
            ",iconName:'" + iconName + '\'' +
            ",servers:" + servers +
            '}';
    }

}
