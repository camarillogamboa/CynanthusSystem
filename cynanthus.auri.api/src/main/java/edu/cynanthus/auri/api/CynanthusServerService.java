package edu.cynanthus.auri.api;

import edu.cynanthus.bean.Config;
import edu.cynanthus.domain.ServerInfo;

/**
 * La interface Cynanthus server service.
 *
 * @param <T> el parámetro de tipo
 */
public interface CynanthusServerService<T extends Config> extends ConfigurationServerService<T> {

    /**
     * La constante CYNANTHUS_SERVER_SERVICE_PATH.
     */
    String CYNANTHUS_SERVER_SERVICE_PATH = AURI_PATH + "/server";

    /**
     * Is available boolean.
     *
     * @param serverInfo el server info
     * @return el boolean
     */
    Boolean isAvailable(ServerInfo serverInfo);

}
