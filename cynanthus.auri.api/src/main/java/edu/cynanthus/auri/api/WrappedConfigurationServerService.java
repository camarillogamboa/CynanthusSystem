package edu.cynanthus.auri.api;

import edu.cynanthus.bean.Config;
import edu.cynanthus.domain.ServerInfo;

import java.util.Objects;

/**
 * El tipo Wrapped configuration server service.
 *
 * @param <T> el parámetro de tipo
 */
public class WrappedConfigurationServerService<T extends Config> implements ConfigurationServerService<T> {

    /**
     * El Configuration server service.
     */
    private final ConfigurationServerService<T> configurationServerService;

    /**
     * Instancia un nuevo Wrapped configuration server service.
     *
     * @param configurationServerService el configuration server service
     */
    public WrappedConfigurationServerService(ConfigurationServerService<T> configurationServerService) {
        this.configurationServerService = Objects.requireNonNull(configurationServerService);
    }

    /**
     * Permite obtener config of.
     *
     * @param serverInfo el server info
     * @return el config of
     */
    @Override
    public T getConfigOf(ServerInfo serverInfo) {
        return configurationServerService.getConfigOf(serverInfo);
    }

    /**
     * Update config of string.
     *
     * @param serverInfo el server info
     * @param config     el config
     * @return el string
     */
    @Override
    public String updateConfigOf(ServerInfo serverInfo, Config config) {
        return configurationServerService.updateConfigOf(serverInfo, config);
    }

    /**
     * Get log files of string [ ].
     *
     * @param serverInfo el server info
     * @return el string [ ]
     */
    @Override
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        return configurationServerService.getLogFilesOf(serverInfo);
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
        return configurationServerService.getLogContentOf(serverInfo, logFileName);
    }

}
