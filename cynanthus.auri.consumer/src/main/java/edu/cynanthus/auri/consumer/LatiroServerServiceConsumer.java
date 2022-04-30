package edu.cynanthus.auri.consumer;

import com.google.gson.reflect.TypeToken;
import edu.cynanthus.auri.api.LatiroServerService;
import edu.cynanthus.common.net.ClientInfo;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.LatiroConfig;

import java.util.List;

class LatiroServerServiceConsumer extends CynanthusServerServiceConsumer<LatiroConfig> implements LatiroServerService {

    LatiroServerServiceConsumer(ClientInfo clientInfo) {
        super(clientInfo, "/cynanthus/auri/server/latiro", LatiroConfig.class);
    }

    @Override
    public List<GeneralNode<SensingNode>> getSensingNodesOf(ServerInfo serverInfo, String selector) {
        checkServerInfo(serverInfo);
        String param = selector == null || selector.isBlank() || selector.isEmpty() ? "*" : selector;
        return consumeApi(
            webConsumer -> webConsumer.GET(resourcePath + "/" + getServerId(serverInfo) + "/node/" + param),
            new TypeToken<List<GeneralNode<SensingNode>>>() {}.getType()
        );
    }

}
