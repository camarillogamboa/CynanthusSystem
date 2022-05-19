package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.StrisServerService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.Indication;
import edu.cynanthus.domain.NodeInfo;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.StrisConfig;

import java.util.function.Consumer;

class SessionBasedStrisServerService
    extends SessionBasedTreeServerService<StrisConfig, ControlNode, StrisServerService>
    implements StrisServerService {

    SessionBasedStrisServerService(
        AuriServiceConsumer<StrisServerService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    @Override
    public Boolean performIndication(ServerInfo serverInfo, Indication indication) {
        return consume(service -> service.performIndication(serverInfo, indication));
    }

    @Override
    public Boolean performIndication(NodeInfo nodeInfo, String instructionName) {
        return consume(service -> service.performIndication(nodeInfo, instructionName));
    }

}
