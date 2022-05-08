package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.StrisServerService;
import edu.cynanthus.auri.api.exception.InvalidArgumentException;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.*;
import edu.cynanthus.domain.config.StrisConfig;

import java.lang.reflect.Type;
import java.util.List;

class StrisServerServiceConsumer
    extends TreeServerServiceConsumer<StrisConfig, ControlNode> implements StrisServerService {

    private static final Type GENERAL_NODE_LIST_TYPE = new TypeToken<List<GeneralNode<ControlNode>>>() {}.getType();

    StrisServerServiceConsumer(LazyRequest lazyRequest) {
        super(lazyRequest, "/cynanthus/auri/server/stris", StrisConfig.class, GENERAL_NODE_LIST_TYPE);
    }

    @Override
    public Boolean performIndication(ServerInfo serverInfo, Indication indication) {
        checkServerInfo(serverInfo);
        if (indication == null) throw new InvalidArgumentException(
            "Se requiere un objeto Indication"
        );

        return consume(
            lazyRequest -> lazyRequest.POST(
                resourcePath + "/" + getServerId(serverInfo) + "/indication",
                () -> JsonProvider.toJsonInputStream(indication)
            ),
            Boolean.class
        );
    }

    @Override
    public Boolean performIndication(NodeInfo nodeInfo, String instructionName) {
        if (nodeInfo == null)
            throw new InvalidArgumentException(
                "Se requiere una instancia de NodeInfo para realizar esta acción"
            );

        if (instructionName == null)
            throw new InvalidArgumentException("Se requiere un nombre de instrucción a ejecutar");

        return consume(lazyRequest ->
                lazyRequest.GET(
                    resourcePath + "/indication/to/" + getNodeId(nodeInfo) + "/with/" + instructionName
                ),
            Boolean.class
        );
    }

    Object getNodeId(NodeInfo bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getName() != null) return bean.getMac();
        else throw new InvalidArgumentException(
                "Se requiere un identificador válido del NodeInfo para realizar esta acción"
            );
    }

}
