package edu.cynanthus.auri.api;

import edu.cynanthus.bean.Config;
import edu.cynanthus.domain.ServerInfo;

public interface CynanthusServerService<T extends Config> extends ConfigurationServerService<T> {

    Boolean isAvailable(ServerInfo serverInfo);

}
