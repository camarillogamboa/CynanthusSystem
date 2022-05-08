package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.NodeInfo;

import java.lang.reflect.Type;
import java.util.List;

class NodeInfoServiceConsumer extends BeanServiceConsumer<NodeInfo> implements NodeInfoService {

    private static final Type NODE_INFO_LIST_TYPE = new TypeToken<List<NodeInfo>>() {}.getType();

    NodeInfoServiceConsumer(LazyRequest lazyRequest) {
        super(
            lazyRequest,
            "/cynanthus/auri/node/info",
            NodeInfo.class,
            NODE_INFO_LIST_TYPE
        );
    }

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

    @Override
    Object getId(NodeInfo bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getName() != null) return bean.getMac();
        else throw new InvalidArgumentException(
                "Se requiere un identificador válido para realizar esta acción"
            );
    }

}
