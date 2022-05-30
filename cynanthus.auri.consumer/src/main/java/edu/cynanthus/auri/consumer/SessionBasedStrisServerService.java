package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.StrisServerService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ControlNode;
import edu.cynanthus.domain.Indication;
import edu.cynanthus.domain.NodeInfo;
import edu.cynanthus.domain.ServerInfo;
import edu.cynanthus.domain.config.StrisConfig;

import java.util.function.Consumer;

/**
 * El tipo Session based stris server service.
 */
class SessionBasedStrisServerService
    extends SessionBasedTreeServerService<StrisConfig, ControlNode, StrisServerService>
    implements StrisServerService {

    /**
     * Instancia un nuevo Session based stris server service.
     *
     * @param auriServiceConsumer el auri service consumer
     * @param sessionStarter      el session starter
     * @param lazyRequestConsumer el lazy request consumer
     */
    SessionBasedStrisServerService(
        AuriServiceConsumer<StrisServerService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
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
        return consume(service -> service.performIndication(serverInfo, indication));
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
        return consume(service -> service.performIndication(nodeInfo, instructionName));
    }

}
