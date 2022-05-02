package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.LatiroServerService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.LatiroConfig;

import java.util.List;

class LatiroServerServiceConsumer extends CynanthusServerServiceConsumer<LatiroConfig> implements LatiroServerService {

    LatiroServerServiceConsumer(LazyRequest lazyRequest) {
        super(lazyRequest, "/cynanthus/auri/server/latiro", LatiroConfig.class);
    }

    @Override
    public List<GeneralNode<SensingNode>> getSensingNodesOf(ServerInfo serverInfo, String selector) {
        checkServerInfo(serverInfo);
        String param = selector == null || selector.isBlank() || selector.isEmpty() ? "*" : selector;
        return consumeService(
            lazyRequest -> lazyRequest.GET(resourcePath + "/" + getServerId(serverInfo) + "/node/" + param),
            new TypeToken<List<GeneralNode<SensingNode>>>() {}.getType()
        );
    }

}
