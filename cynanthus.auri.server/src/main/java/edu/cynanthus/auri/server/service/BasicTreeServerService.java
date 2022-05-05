package edu.cynanthus.auri.server.service;

import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.auri.api.TreeServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.domain.*;

import java.lang.reflect.Type;
import java.util.*;

class BasicTreeServerService<T extends Config, N extends RuntimeNode>
    extends BasicCynanthusServerService<T> implements TreeServerService<T, N> {

    private final NodeInfoService nodeInfoService;
    private final Type runtimeNodeListType;

    BasicTreeServerService(
        ServerInfoService serverInfoService,
        NodeInfoService nodeInfoService,
        String basePath,
        Class<T> configClass,
        ServerType serverType,
        Type runtimeNodeListType
    ) {
        super(serverInfoService, basePath, configClass, serverType);
        this.nodeInfoService = Objects.requireNonNull(nodeInfoService);
        this.runtimeNodeListType = runtimeNodeListType;
    }

    @Override
    public List<GeneralNode<N>> getGeneralNodesOf(ServerInfo serverInfo, String selector) {
        ServerInfo fullServerInfo = serverInfoService.read(serverInfo);
        checkServerType(fullServerInfo);

        List<? extends NodeInfo> nodeInfos = nodeInfoService.readAllByIdServerInfo(fullServerInfo.getId());

        List<N> runtimeNodes = consume(
            lazyRequest -> lazyRequest.GET(buildUri(fullServerInfo, "/node")),
            runtimeNodeListType
        );

        Map<String, GeneralNode<N>> map = new TreeMap<>(String::compareTo);

        for (NodeInfo nodeInfo : nodeInfos)
            map.put(nodeInfo.getMac(), new GeneralNode<>(nodeInfo, null));

        for (N runtimeNode : runtimeNodes) {
            GeneralNode<N> generalNode = map.get(runtimeNode.getMac());

            if (generalNode != null) {
                generalNode.setRuntimeNode(runtimeNode);
            } else {
                generalNode = new GeneralNode<>(null, runtimeNode);
                map.put(runtimeNode.getMac(), generalNode);
            }
        }

        return new ArrayList<>(map.values());
    }

}
