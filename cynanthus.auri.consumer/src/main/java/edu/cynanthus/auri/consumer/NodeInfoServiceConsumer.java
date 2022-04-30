package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.NodeInfoService;
import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.common.net.ClientInfo;
import edu.cynanthus.domain.NodeInfo;

import java.util.List;

class NodeInfoServiceConsumer extends BeanServiceConsumer<NodeInfo> implements NodeInfoService {

    NodeInfoServiceConsumer(ClientInfo clientInfo) {
        super(
            clientInfo,
            "/cynanthus/auri/node/info",
            NodeInfo.class,
            new TypeToken<List<NodeInfo>>() {}.getType()
        );
    }

    @Override
    public List<? extends NodeInfo> readAllByIdServerInfo(Integer idServerInfo) {
        if (idServerInfo == null)
            throw new ServiceException(
                "Se requiere un identificador para realizar esta acción",
                ExceptionType.REQUIRED_DATA
            );

        return consumeApi(
            webServiceConsumer -> webServiceConsumer.GET("/of/" + idServerInfo),
            new TypeToken<List<NodeInfo>>() {}.getType()
        );
    }

    @Override
    Object getId(NodeInfo bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getName() != null) return bean.getMac();
        else throw new ServiceException(
                "Se requiere un identificador válido para realizar esta acción",
                ExceptionType.REQUIRED_DATA
            );
    }

}
