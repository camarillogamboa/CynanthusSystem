package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.TreeServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.RuntimeNode;
import edu.cynanthus.domain.ServerInfo;

import java.lang.reflect.Type;
import java.util.List;

/**
 * El tipo Tree server service consumer.
 *
 * @param <T> el parámetro de tipo
 * @param <N> el parámetro de tipo
 */
class TreeServerServiceConsumer<T extends Config, N extends RuntimeNode>
    extends CynanthusServerServiceConsumer<T> implements TreeServerService<T, N> {

    /**
     * El General node list type.
     */
    private final Type generalNodeListType;

    /**
     * Instancia un nuevo Tree server service consumer.
     *
     * @param lazyRequest         el lazy request
     * @param resourcePath        el resource path
     * @param configType          el config type
     * @param generalNodeListType el general node list type
     */
    TreeServerServiceConsumer(LazyRequest lazyRequest, String resourcePath, Type configType, Type generalNodeListType) {
        super(lazyRequest, resourcePath, configType);
        this.generalNodeListType = generalNodeListType;
    }

    /**
     * Permite obtener general nodes of.
     *
     * @param serverInfo el server info
     * @param selector   el selector
     * @return el general nodes of
     */
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
