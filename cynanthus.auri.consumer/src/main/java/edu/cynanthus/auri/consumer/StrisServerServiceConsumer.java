package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.StrisServerService;
import edu.cynanthus.auri.api.error.NullPointerServiceException;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.common.resource.StreamUtil;
import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.Indication;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.StrisConfig;

import java.lang.reflect.Type;
import java.util.List;

class StrisServerServiceConsumer extends CynanthusServerServiceConsumer<StrisConfig> implements StrisServerService {

    private static final Type GENERAL_NODE_LIST_TYPE = new TypeToken<List<GeneralNode<ControlNode>>>() {}.getType();

    StrisServerServiceConsumer(LazyRequest lazyRequest) {
        super(lazyRequest, "/cynanthus/auri/server/stris", StrisConfig.class);
    }

    @Override
    public Boolean performIndication(ServerInfo serverInfo, Indication indication) {
        checkServerInfo(serverInfo);
        if (indication == null) throw new NullPointerServiceException(
            "Se requiere un objeto Indication"
        );

        return consume(
            lazyRequest -> lazyRequest.POST(
                "/" + getServerId(serverInfo) + "/indication",
                () -> StreamUtil.asInputStream(JsonProvider.toJson(indication))
            ),
            Boolean.class
        );
    }

    @Override
    public List<GeneralNode<ControlNode>> getControlNodesOf(ServerInfo serverInfo, String selector) {
        checkServerInfo(serverInfo);
        return consume(
            lazyRequest -> lazyRequest.GET("/" + getServerId(serverInfo) + "/node/" + selector),
            GENERAL_NODE_LIST_TYPE
        );
    }

}
