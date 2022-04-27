package edu.cynanthus.auri.api;

import edu.cynanthus.bean.Config;
import edu.cynanthus.domain.ServerInfo;

public interface ConfigurationServerService<T extends Config> {

    T getConfigOf(ServerInfo serverInfo);

    String updateConfigOf(ServerInfo serverInfo, Config config);

    String[] getLogFilesOf(ServerInfo serverInfo);

    String getLogContentOf(ServerInfo serverInfo, String logFileName);

}
