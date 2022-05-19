package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.ConfigurationServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ServerInfo;

import java.util.function.Consumer;

class SessionBasedConfigurationServerService<T extends Config, S extends ConfigurationServerService<T>>
    extends SessionBasedService<S> implements ConfigurationServerService<T> {

    SessionBasedConfigurationServerService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    @Override
    public T getConfigOf(ServerInfo serverInfo) {
        return consume(service -> service.getConfigOf(serverInfo));
    }

    @Override
    public Boolean updateConfigOf(ServerInfo serverInfo, T config) {
        return consume(service -> service.updateConfigOf(serverInfo, config));
    }

    @Override
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        return consume(service -> service.getLogFilesOf(serverInfo));
    }

    @Override
    public String getLogContentOf(ServerInfo serverInfo, String logFileName) {
        return consume(service -> service.getLogContentOf(serverInfo, logFileName));
    }

}
