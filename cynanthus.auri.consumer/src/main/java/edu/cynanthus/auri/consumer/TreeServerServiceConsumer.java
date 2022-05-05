package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.TreeServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.RuntimeNode;
import edu.cynanthus.domain.ServerInfo;

import java.lang.reflect.Type;
import java.util.List;

class TreeServerServiceConsumer<T extends Config, N extends RuntimeNode>
    extends CynanthusServerServiceConsumer<T> implements TreeServerService<T, N> {

    private final Type generalNodeListType;

    TreeServerServiceConsumer(LazyRequest lazyRequest, String resourcePath, Type configType, Type generalNodeListType) {
        super(lazyRequest, resourcePath, configType);
        this.generalNodeListType = generalNodeListType;
    }

    @Override
    public List<GeneralNode<N>> getGeneralNodesOf(ServerInfo serverInfo, String selector) {
        checkServerInfo(serverInfo);
        String param = selector == null || selector.isBlank() || selector.isEmpty() ? "*" : selector;
        return consume(
            lazyRequest -> lazyRequest.GET(resourcePath + "/" + getServerId(serverInfo) + "/node/" + param),
            generalNodeListType
        );
    }

}
