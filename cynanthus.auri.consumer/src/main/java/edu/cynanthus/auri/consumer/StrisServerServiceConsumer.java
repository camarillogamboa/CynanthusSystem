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

/**
 * El tipo Stris server service consumer.
 */
class StrisServerServiceConsumer
    extends TreeServerServiceConsumer<StrisConfig, ControlNode> implements StrisServerService {

    /**
     * La constante GENERAL_NODE_LIST_TYPE.
     */
    private static final Type GENERAL_NODE_LIST_TYPE = new TypeToken<List<GeneralNode<ControlNode>>>() {}.getType();

    /**
     * Instancia un nuevo Stris server service consumer.
     *
     * @param lazyRequest el lazy request
     */
    StrisServerServiceConsumer(LazyRequest lazyRequest) {
        super(lazyRequest, STRIS_SERVER_SERVICE_PATH, StrisConfig.class, GENERAL_NODE_LIST_TYPE);
    }

    /**
     * Perform indication boolean.
     *
     * @param serverInfo el server info
     * @param indication el indication
     * @return el boolean
     */
    @Override
    public Boolean performIndication(ServerInfo serverInfo, Indication indication) {
        checkServerInfo(serverInfo);
        if (indication == null) throw new InvalidArgumentException(
            "Se requiere un objeto Indication"
        );

        return sendAndConsume(
            lazyRequest -> lazyRequest.POST(
                resourcePath + "/" + getServerId(serverInfo) + "/indication",
                () -> JsonProvider.toJsonInputStream(indication)
            ),
            Boolean.class
        );
    }

    /**
     * Perform indication boolean.
     *
     * @param nodeInfo        el node info
     * @param instructionName el instruction name
     * @return el boolean
     */
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

    /**
     * Permite obtener node id.
     *
     * @param bean el bean
     * @return el node id
     */
    Object getNodeId(NodeInfo bean) {
        if (bean.getId() != null) return bean.getId();
        else if (bean.getName() != null) return bean.getMac();
        else throw new InvalidArgumentException(
                "Se requiere un identificador válido del NodeInfo para realizar esta acción"
            );
    }

}
