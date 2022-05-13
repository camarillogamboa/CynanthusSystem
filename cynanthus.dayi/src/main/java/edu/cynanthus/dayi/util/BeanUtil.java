package edu.cynanthus.dayi.util;

import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.ServerType;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public final class BeanUtil {

    private BeanUtil() {}

    public static List<ServerInfo> getStorageServers(List<? extends ServerInfo> serverInfos) {
        return filterAndCollectServerInfos(serverInfos, ServerType.STORAGE);
    }

    public static List<ServerInfo> getDataStreamServers(List<? extends ServerInfo> serverInfos) {
        return filterAndCollectServerInfos(serverInfos, ServerType.STREAM_DATA);
    }

    public static List<ServerInfo> getControlServers(List<? extends ServerInfo> serverInfos) {
        return filterAndCollectServerInfos(serverInfos, ServerType.CONTROL);
    }

    public static List<ServerCategory> toCategories(List<? extends ServerInfo> serverInfos) {
        List<ServerCategory> serverCategories = new LinkedList<>();
        serverCategories.add(
            new ServerCategory("Servidores de almacenamiento", getStorageServers(serverInfos))
        );
        serverCategories.add(
            new ServerCategory("Servidores de flujo de datos", getDataStreamServers(serverInfos))
        );
        serverCategories.add(
            new ServerCategory("Servidores de control", getControlServers(serverInfos))
        );
        return serverCategories;
    }

    private static List<ServerInfo> filterAndCollectServerInfos(
        List<? extends ServerInfo> serverInfos,
        ServerType serverType
    ) {
        return serverInfos
            .stream()
            .filter(serverInfo -> serverInfo.getServerType().equals(serverType))
            .collect(Collectors.toList());
    }


}
