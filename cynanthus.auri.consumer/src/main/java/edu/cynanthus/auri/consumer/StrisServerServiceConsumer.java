package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.ExceptionType;
import edu.cynanthus.auri.api.ServiceException;
import edu.cynanthus.auri.api.StrisServerService;
import edu.cynanthus.common.json.JsonProvider;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.common.resource.StreamUtil;
import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.Indication;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.StrisConfig;

import java.util.List;

class StrisServerServiceConsumer extends CynanthusServerServiceConsumer<StrisConfig> implements StrisServerService {

    StrisServerServiceConsumer(LazyRequest lazyRequest) {
        super(lazyRequest, "/cynanthus/auri/server/stris", StrisConfig.class);
    }

    @Override
    public Boolean performIndication(ServerInfo serverInfo, Indication indication) {
        checkServerInfo(serverInfo);
        if (indication == null) throw new ServiceException(
            "Se requiere un objeto Indication",
            ExceptionType.REQUIRED_DATA
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
            lazyRequest -> lazyRequest.GET("/"+getServerId(serverInfo)+"/node/"+selector),
            new TypeToken<List<GeneralNode<ControlNode>>>(){}.getType()
        );
    }

}
