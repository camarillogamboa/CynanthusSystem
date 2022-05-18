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

    public static List<ServerSection> toServerSections(List<? extends ServerInfo> serverInfos) {
        List<ServerSection> serverSections = new LinkedList<>();
        serverSections.add(
            new ServerSection("Almacenamiento","fas fa-hdd", getStorageServers(serverInfos))
        );
        serverSections.add(
            new ServerSection("Flujo de datos","fas fa-stream", getDataStreamServers(serverInfos))
        );
        serverSections.add(
            new ServerSection("Control","fas fa-grip-horizontal", getControlServers(serverInfos))
        );
        return serverSections;
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
