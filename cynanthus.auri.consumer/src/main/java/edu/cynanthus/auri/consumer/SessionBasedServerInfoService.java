package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.ServerInfoService;
import edu.cynanthus.domain.ServerInfo;

import java.util.Map;
import java.util.function.Supplier;

class SessionBasedServerInfoService
    extends SessionBasedBeanService<ServerInfo, ServerInfoService> implements ServerInfoService {

    SessionBasedServerInfoService(
        AuriServiceConsumer<ServerInfoService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Supplier<Map<String, String>> headersSupplier
    ) {
        super(auriServiceConsumer, sessionStarter, headersSupplier);
    }

}
