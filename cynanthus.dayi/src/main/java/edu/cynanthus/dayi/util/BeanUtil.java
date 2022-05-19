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

        List<ServerInfo> storageServers = getStorageServers(serverInfos);
        List<ServerInfo> dataStreamServers = getDataStreamServers(serverInfos);
        List<ServerInfo> controlServers = getControlServers(serverInfos);

        if (!storageServers.isEmpty())
            serverSections.add(
                new ServerSection("Almacenamiento", "fas fa-hdd", storageServers)
            );

        if (!dataStreamServers.isEmpty())
            serverSections.add(
                new ServerSection("Flujo de datos", "fas fa-stream", dataStreamServers)
            );

        if (!controlServers.isEmpty())
            serverSections.add(
                new ServerSection("Control", "fas fa-grip-horizontal", controlServers)
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
