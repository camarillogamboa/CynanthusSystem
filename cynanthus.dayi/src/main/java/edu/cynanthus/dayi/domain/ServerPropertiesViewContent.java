package edu.cynanthus.dayi.domain;

import edu.cynanthus.domain.ServerInfo;

import java.util.List;
import java.util.Objects;

public class ServerPropertiesViewContent extends ServerViewContent {

    private final List<PropertyInfo> propertyInfos;

    public ServerPropertiesViewContent(ServerInfo serverInfo, Boolean available, List<PropertyInfo> propertyInfos) {
        super(serverInfo, available);
        this.propertyInfos = propertyInfos;
    }

    public List<PropertyInfo> getPropertyInfos() {
        return propertyInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ServerPropertiesViewContent that = (ServerPropertiesViewContent) o;
        return Objects.equals(propertyInfos, that.propertyInfos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), propertyInfos);
    }

    @Override
    public String toString() {
        return "{" +
            super.toString() + "," +
            "propertyInfos:" + propertyInfos +
            '}';
    }

}
