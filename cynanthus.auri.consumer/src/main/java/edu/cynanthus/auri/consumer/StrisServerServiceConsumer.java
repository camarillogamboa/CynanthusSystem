package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.StrisServerService;
import edu.cynanthus.common.net.ClientInfo;
import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.GeneralNode;
import edu.cynanthus.domain.Indication;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.StrisConfig;

import java.util.List;

class StrisServerServiceConsumer extends CynanthusServerServiceConsumer<StrisConfig> implements StrisServerService {

    StrisServerServiceConsumer(ClientInfo clientInfo) {
        super(clientInfo, "/cynanthus/auri/server/stris", StrisConfig.class);
    }

    @Override
    public Boolean performIndication(ServerInfo serverInfo, Indication indication) {
        return null;
    }

    @Override
    public List<GeneralNode<ControlNode>> getControlNodesOf(ServerInfo serverInfo, String selector) {
        return null;
    }

}
