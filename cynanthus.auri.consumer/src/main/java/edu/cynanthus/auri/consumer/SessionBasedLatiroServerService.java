package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.LatiroServerService;
import edu.cynanthus.domain.SensingNode;
import edu.cynanthus.domain.config.LatiroConfig;

import java.util.Map;
import java.util.function.Supplier;

class SessionBasedLatiroServerService
    extends SessionBasedTreeServerService<LatiroConfig, SensingNode, LatiroServerService>
    implements LatiroServerService {

    SessionBasedLatiroServerService(
        AuriServiceConsumer<LatiroServerService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Supplier<Map<String, String>> headersSupplier
    ) {
        super(auriServiceConsumer, sessionStarter, headersSupplier);
    }

}
