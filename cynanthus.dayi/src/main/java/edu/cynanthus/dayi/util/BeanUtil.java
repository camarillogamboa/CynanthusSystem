package edu.cynanthus.dayi.util;

import edu.cynanthus.dayi.domain.ServerSection;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.ServerType;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class BeanUtil {

    private BeanUtil() {}

    public static List<ServerSection> toServerSections(List<? extends ServerInfo> serverInfos) {
        List<ServerSection> serverSections = new LinkedList<>();

        Map<ServerType, List<ServerInfo>> groups = serverInfos.stream()
            .collect(Collectors.groupingBy(ServerInfo::getServerType));

        List<ServerInfo> storageServers = groups.get(ServerType.STORAGE);
        List<ServerInfo> dataStreamServers = groups.get(ServerType.STREAM_DATA);
        List<ServerInfo> controlServers = groups.get(ServerType.CONTROL);

        if (storageServers != null && !storageServers.isEmpty())
            serverSections.add(
                new ServerSection("Almacenamiento", "fas fa-hdd", storageServers)
            );

        if (dataStreamServers != null && !dataStreamServers.isEmpty())
            serverSections.add(
                new ServerSection("Flujo de datos", "fas fa-stream", dataStreamServers)
            );

        if (controlServers != null && !controlServers.isEmpty())
            serverSections.add(
                new ServerSection("Control", "fas fa-grip-horizontal", controlServers)
            );

        return serverSections;
    }

}
