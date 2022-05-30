package edu.cynanthus.auri.consumer;

import edu.cynanthus.auri.api.CynanthusServerService;
import edu.cynanthus.bean.Config;
import edu.cynanthus.common.net.http.client.LazyRequest;
import edu.cynanthus.domain.ServerInfo;

import java.lang.reflect.Type;

/**
 * El tipo Cynanthus server service consumer.
 *
 * @param <T> el parámetro de tipo
 */
class CynanthusServerServiceConsumer<T extends Config>
    extends ConfigurationServerServiceConsumer<T> implements CynanthusServerService<T> {

    /**
     * Instancia un nuevo Cynanthus server service consumer.
     *
     * @param lazyRequest  el lazy request
     * @param resourcePath el resource path
     * @param configType   el config type
     */
    CynanthusServerServiceConsumer(LazyRequest lazyRequest, String resourcePath, Type configType) {
        super(lazyRequest, resourcePath, configType);
    }

    /**
     * Is available boolean.
     *
     * @param serverInfo el server info
     * @return el boolean
     */
    @Override
    public Boolean isAvailable(ServerInfo serverInfo) {
        checkServerInfo(serverInfo);
        return consume(
            lazyRequest -> lazyRequest.GET(resourcePath + "/" + getServerId(serverInfo) + "/available"),
            Boolean.class
        );
    }

}
