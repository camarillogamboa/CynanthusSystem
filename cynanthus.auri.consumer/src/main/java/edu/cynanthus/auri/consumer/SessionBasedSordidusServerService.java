package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.SordidusServerService;
import edu.cynanthus.domain.config.SordidusConfig;

import java.util.Map;
import java.util.function.Supplier;

class SessionBasedSordidusServerService
    extends SessionBasedCynanthusServerService<SordidusConfig, SordidusServerService> implements SordidusServerService {

    SessionBasedSordidusServerService(
        AuriServiceConsumer<SordidusServerService> auriServiceConsumer,
        SessionStarter sessionStarter,
        Supplier<Map<String, String>> headersSupplier
    ) {
        super(auriServiceConsumer, sessionStarter, headersSupplier);
    }

}
