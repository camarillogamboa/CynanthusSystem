package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.ConfigurationServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ServerInfo;

import java.util.function.Consumer;

/**
 * El tipo Session based configuration server service.
 *
 * @param <T> el parámetro de tipo
 * @param <S> el parámetro de tipo
 */
class SessionBasedConfigurationServerService<T extends Config, S extends ConfigurationServerService<T>>
    extends SessionBasedService<S> implements ConfigurationServerService<T> {

    /**
     * Instancia un nuevo Session based configuration server service.
     *
     * @param auriServiceConsumer el auri service consumer
     * @param sessionStarter      el session starter
     * @param lazyRequestConsumer el lazy request consumer
     */
    SessionBasedConfigurationServerService(
        AuriServiceConsumer<S> auriServiceConsumer,
        SessionStarter sessionStarter,
        Consumer<LazyRequest> lazyRequestConsumer
    ) {
        super(auriServiceConsumer, sessionStarter, lazyRequestConsumer);
    }

    /**
     * Permite obtener config of.
     *
     * @param serverInfo el server info
     * @return el config of
     */
    @Override
    public T getConfigOf(ServerInfo serverInfo) {
        return consume(service -> service.getConfigOf(serverInfo));
    }

    /**
     * Update config of boolean.
     *
     * @param serverInfo el server info
     * @param config     el config
     * @return el boolean
     */
    @Override
    public Boolean updateConfigOf(ServerInfo serverInfo, T config) {
        return consume(service -> service.updateConfigOf(serverInfo, config));
    }

    /**
     * Get log files of string [ ].
     *
     * @param serverInfo el server info
     * @return el string [ ]
     */
    @Override
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        return consume(service -> service.getLogFilesOf(serverInfo));
    }

    /**
     * Permite obtener log content of.
     *
     * @param serverInfo  el server info
     * @param logFileName el log file name
     * @return el log content of
     */
    @Override
    public String getLogContentOf(ServerInfo serverInfo, String logFileName) {
        return consume(service -> service.getLogContentOf(serverInfo, logFileName));
    }

}
