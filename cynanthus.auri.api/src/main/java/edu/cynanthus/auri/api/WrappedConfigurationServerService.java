package edu.cynanthus.auri.api;

import edu.cynanthus.bean.Config;
import edu.cynanthus.domain.ServerInfo;

import java.util.Objects;

public class WrappedConfigurationServerService<T extends Config> implements ConfigurationServerService<T> {

    private final ConfigurationServerService<T> configurationServerService;

    public WrappedConfigurationServerService(ConfigurationServerService<T> configurationServerService) {
        this.configurationServerService = Objects.requireNonNull(configurationServerService);
    }

    @Override
    public T getConfigOf(ServerInfo serverInfo) {
        return configurationServerService.getConfigOf(serverInfo);
    }

    @Override
    public String updateConfigOf(ServerInfo serverInfo, Config config) {
        return configurationServerService.updateConfigOf(serverInfo, config);
    }

    @Override
    public String[] getLogFilesOf(ServerInfo serverInfo) {
        return configurationServerService.getLogFilesOf(serverInfo);
    }

    @Override
    public String getLogContentOf(ServerInfo serverInfo, String logFileName) {
        return configurationServerService.getLogContentOf(serverInfo, logFileName);
    }

}
