package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.LatiroServerService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.config.LatiroConfig;

import java.util.function.Consumer;

/**
 * El tipo Session based latiro server service.
 */
class SessionBasedLatiroServerService
    extends SessionBasedTreeServerService<LatiroConfig, SensingNode, LatiroServerService>
    implements LatiroServerService {

    /**
     * Instancia un nuevo Session based latiro server service.
     *
     * @param auriServiceConsumer el auri service consumer
     * @param sessionStarter      el session starter
     * @param lazyRequestConsumer el lazy request consumer
     */
    SessionBasedLatiroServerService(
        AuriServiceConsumer<LatiroServerService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

}
