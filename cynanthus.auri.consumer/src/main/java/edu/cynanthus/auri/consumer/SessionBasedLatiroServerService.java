package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.LatiroServerService;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.config.LatiroConfig;

import java.util.function.Consumer;

class SessionBasedLatiroServerService
    extends SessionBasedTreeServerService<LatiroConfig, SensingNode, LatiroServerService>
    implements LatiroServerService {

    SessionBasedLatiroServerService(
        AuriServiceConsumer<LatiroServerService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

}
