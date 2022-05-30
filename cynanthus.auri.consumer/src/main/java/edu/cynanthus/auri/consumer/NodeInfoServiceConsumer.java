package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.NodeInfo;

import java.lang.reflect.Type;
import java.util.List;

/**
 * El tipo Node info service consumer.
 */
class NodeInfoServiceConsumer extends BeanServiceConsumer<NodeInfo> implements NodeInfoService {

    /**
     * La constante NODE_INFO_LIST_TYPE.
     */
    private static final Type NODE_INFO_LIST_TYPE = new TypeToken<List<NodeInfo>>() {}.getType();

    /**
     * Instancia un nuevo Node info service consumer.
     *
     * @param lazyRequest el lazy request
     */
    NodeInfoServiceConsumer(LazyRequest lazyRequest) {
        super(
            lazyRequest,
            NODE_INFO_SERVICE_PATH,
            NodeInfo.class,
            NODE_INFO_LIST_TYPE
        );
    }

    /**
     * Read all by id server info list.
     *
     * @param idServerInfo el id server info
     * @return el list
     */
    @Override
    public List<? extends NodeInfo> readAllByIdServerInfo(Integer idServerInfo) {
        if (idServerInfo == null)
            throw new InvalidArgumentException(
                "Se requiere un identificador para realizar esta acción"
            );

        return consume(
            lazyRequest -> lazyRequest.GET(resourcePath + "/of/" + idServerInfo),
            NODE_INFO_LIST_TYPE
        );
    }

    /**
     * Permite obtener id.
     *
     * @param bean el bean
     * @return el id
     */
    @Override
    Object getId(NodeInfo bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getName() != null) return bean.getMac();
        else throw new InvalidArgumentException(
                "Se requiere un identificador válido para realizar esta acción"
            );
    }

}
